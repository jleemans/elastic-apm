package eu.deltasource.apm.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import eu.deltasource.apm.database.User;
import org.junit.jupiter.api.Test;

class RandomUserServiceTest {
	private final RandomUserService randomUserService = new RandomUserService();

	@Test
	void normalResponseShouldParse() {
		responseShouldParse(normalResponse());
	}

	@Test
	void nameWithDashesResponseShouldNotParse() {
		final String response = nameContainingDashesResponse();
		assertThrows(IllegalArgumentException.class, () -> responseShouldParse(response));
	}

	@Test
	void nameWithNonAsciiCharactersShouldNotParse() {
		final String response = nameWithNonAsciiCharactersResponse();
		assertThrows(IllegalArgumentException.class, () -> responseShouldParse(response));
	}
	private void responseShouldParse(String response) {
		// WHEN
		User user = randomUserService.parseResponse(response);

		// THEN
		assertNotNull(user);
		assertNotNull(user.getName());
		assertNotNull(user.getEmail());
	}

	private String normalResponse() {
		return "{\"results\":[{\"gender\":\"male\",\"name\":{\"title\":\"Mr\",\"first\":\"Michael\",\"last\":\"Wheeler\"},\"location\":{\"street\":{\"number\":8461,\"name\":\"Springfield Road\"},\"city\":\"Winchester\",\"state\":\"Somerset\",\"country\":\"United Kingdom\",\"postcode\":\"LK76 2TU\",\"coordinates\":{\"latitude\":\"-88.2224\",\"longitude\":\"-102.4230\"},\"timezone\":{\"offset\":\"-9:00\",\"description\":\"Alaska\"}},\"email\":\"michael.wheeler@example.com\",\"login\":{\"uuid\":\"bf1dcfc2-37b7-4e86-b3b0-dcd5c7b38f73\",\"username\":\"silverbear856\",\"password\":\"coach\",\"salt\":\"mCTE6IzU\",\"md5\":\"1ec7f6f513e2a85c73c834d2b248df4b\",\"sha1\":\"2491647e0b308bb1d095509b18392facb38bb608\",\"sha256\":\"1c89264fcd8361a724aea3d79719dbf1e820f77e6c25bec7bf34a521539ece05\"},\"dob\":{\"date\":\"1988-09-12T11:59:11.888Z\",\"age\":36},\"registered\":{\"date\":\"2018-08-15T17:32:30.317Z\",\"age\":6},\"phone\":\"016977 71871\",\"cell\":\"07361 947259\",\"id\":{\"name\":\"NINO\",\"value\":\"SZ 33 28 36 U\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/men/31.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/men/31.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/men/31.jpg\"},\"nat\":\"GB\"}],\"info\":{\"seed\":\"c1d9986cf5a7da33\",\"results\":1,\"page\":1,\"version\":\"1.4\"}}";
	}
	private String nameContainingDashesResponse() {
		return "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"Miss\",\"first\":\"Lily\",\"last\":\"Jean-Baptiste\"},\"location\":{\"street\":{\"number\":8780,\"name\":\"Dundas Rd\"},\"city\":\"Richmond\",\"state\":\"Saskatchewan\",\"country\":\"Canada\",\"postcode\":\"O1T 0T4\",\"coordinates\":{\"latitude\":\"-66.9187\",\"longitude\":\"19.6394\"},\"timezone\":{\"offset\":\"+8:00\",\"description\":\"Beijing, Perth, Singapore, Hong Kong\"}},\"email\":\"lily.jean-baptiste@example.com\",\"login\":{\"uuid\":\"b62de6bd-e60f-4f7e-9e6d-8218d5f43314\",\"username\":\"smallleopard255\",\"password\":\"garbage\",\"salt\":\"za77B4f6\",\"md5\":\"dad9a8ee34ec5f1bed8735bcf23d3185\",\"sha1\":\"2ea1a518da96f13a69ce50642e40fec27034fafb\",\"sha256\":\"a88b5d836c7c1d0d94d105e1720db1ee5f017d357fd4bec7d2ad62bd64ce47ad\"},\"dob\":{\"date\":\"1950-01-08T19:07:04.805Z\",\"age\":74},\"registered\":{\"date\":\"2013-04-15T14:24:12.328Z\",\"age\":11},\"phone\":\"I92 F21-2707\",\"cell\":\"D26 Y64-6960\",\"id\":{\"name\":\"SIN\",\"value\":\"591382981\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/81.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/81.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/81.jpg\"},\"nat\":\"CA\"}],\"info\":{\"seed\":\"64bb8a7d875efb1c\",\"results\":1,\"page\":1,\"version\":\"1.4\"}}";
	}
	private String nameWithNonAsciiCharactersResponse() {
		return "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"Mrs\",\"first\":\"فاطمه زهرا\",\"last\":\"پارسا\"},\"location\":{\"street\":{\"number\":3674,\"name\":\"شهید شواخ\"},\"city\":\"شهریار\",\"state\":\"کرمان\",\"country\":\"Iran\",\"postcode\":22928,\"coordinates\":{\"latitude\":\"18.1556\",\"longitude\":\"138.3211\"},\"timezone\":{\"offset\":\"-4:00\",\"description\":\"Atlantic Time (Canada), Caracas, La Paz\"}},\"email\":\"ftmhzhr.prs@example.com\",\"login\":{\"uuid\":\"9c84579d-2de6-4cc4-a2c1-ee13a48da7f4\",\"username\":\"beautifultiger930\",\"password\":\"room\",\"salt\":\"33yYXt3k\",\"md5\":\"45d07d481213a3624a5b79b063935f79\",\"sha1\":\"6a74a05d5e2fb4a1babcdad8daad4b5100a3785f\",\"sha256\":\"4b66b50d9676689bbe3059cf433af9d5ae177ebf5eef000b402fc975a51d617c\"},\"dob\":{\"date\":\"1950-08-13T04:30:46.272Z\",\"age\":74},\"registered\":{\"date\":\"2007-03-24T07:52:29.167Z\",\"age\":17},\"phone\":\"035-86492652\",\"cell\":\"0913-797-9548\",\"id\":{\"name\":\"\",\"value\":null},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/64.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/64.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/64.jpg\"},\"nat\":\"IR\"}],\"info\":{\"seed\":\"6b1730109fb2bedd\",\"results\":1,\"page\":1,\"version\":\"1.4\"}}";
	}
}
