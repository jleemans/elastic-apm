-- loadtest.lua
--
-- Prerequisites:
-- Install `wrk`, using: `brew install wrk`.

-- Execute this script using:
-- wrk -t10 -c200 -d30s -s wrk_post_get.lua http://localhost:8080
-- This will run the script with 10 threads in parallel, keeping 200 connections open to the server and running for 30 seconds.
-- The script itself alternates randomly between POST and GET.

user_ids = {}

-- Function to generate a random string for names/emails
function random_string(length)
	local charset = {}
	for i = 48, 57 do
		table.insert(charset, string.char(i))
	end -- 0-9
	for i = 65, 90 do
		table.insert(charset, string.char(i))
	end -- A-Z
	for i = 97, 122 do
		table.insert(charset, string.char(i))
	end -- a-z

	local result = ""
	for i = 1, length do
		result = result .. charset[math.random(1, #charset)]
	end
	return result
end

-- Function to decide between POST and GET
function request()
	-- Randomly decide to do a POST (create user) or GET (retrieve user)
	if #user_ids == 0 or math.random() < 0.5 then
		return make_post_request()
	else
		return make_get_request()
	end
end

-- Function to create a POST request to create a new user
function make_post_request()
	local name = random_string(8)
	local email = random_string(5) .. "@example.com"
	local body = string.format('{"name": "%s", "email": "%s"}', name, email)

	return wrk.format("POST", "/api/v1/users", { ["Content-Type"] = "application/json" }, body)
end

-- Function to create a GET request to retrieve a user by ID
function make_get_request()
	local id = user_ids[math.random(1, #user_ids)]
	local path = string.format("/api/v1/users/%s", id)

	return wrk.format("GET", path)
end

-- Function to parse POST response and extract user ID
function response(status, headers, body)
	print("Parsing response, status is ", status)
	-- Assuming the response is a JSON object with an "id" field
	if status == 201 or status == 200 then
		local id = body:match('"id"%s*:%s*(%d+)')
		if id then
			table.insert(user_ids, id)
		end
	end
end
