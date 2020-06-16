/**
 *
 */
package vn.chuyenviet.sdk.web.demo.utils;

/**
 * @author HaiNT
 */
public class ApplicationMessage {
	public static final String DUPLICATE_EMAIL = "duplicate-email";
	public static final String DUPLICATE_RECOMMENDED_EMAIL = "duplicate-recommended-email";
	public static final String UNAUTHORISED = "unauthorized";
    public static final String LOGIN_FAIL = "login-fail";
	public static final String REGISTER_FAIL = "register-fail";
    public static final String NOT_FOUND_EXCEPTION = "not-found-exception";
    public static final String NOT_FOUND = "not-found";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String DELETE_FAIL = "delete-fail";
    public static final String USER_ID_NULL = "user-id-null";
    public static final String USER_EMAIL_NULL = "user-email-null";
    public static final String USER_EMAIL_EXIST = "user-email-exist";
    public static final String EMAIL_DOES_NOT_EXIST = "email-does-not-exist";
    public static final String USER_NOT_EXIST = "user-not-exist";
    public static final String USER_FIRST_NULL = "user-firstname-null";
    public static final String USER_LAST_NULL = "user-lastname-null";
    public static final String USER_USERNAME_NULL = "user-username-null";
    public static final String FUNCTION_NAME_NULL = "function-name-null";
    public static final String FUNCTION_AUTORITYKEY_NULL = "function-autoritykey-null";
    public static final String FUNCTIONROLE_AUTHORITYID_NULL = "functionrole-functionid-null";
    public static final String FUNCTIONUSER_USERID_NULL = "functionuser-userid-null";
    public static final String FUNCTIONUSER_FUNCTIONID_NULL = "functionuser-functionid-null";
    public static final String AUTHORITY_NAME_NULL = "authority-name-null";
    public static final String ORGANIZATION_CODE_NULL = "organization-code-null";
    public static final String ORGANIZATION_CREATEDFOR_NULL = "organization-createfor-null";
    public static final String WHISTORY_ENTITYTYPE_NULL = "whistory-entitytype-null";
    public static final String WHISTORY_ENTITYID_NULL = "whistory-entityid-null";
    public static final String WHISTORY_ACTEDBY_NULL = "whistory-actedby-null";
    public static final String WHISTORY_ACTEDON_NULL = "whistory-actedon-null";
    public static final String WHISTORY_ENTITYSTATUS_NULL = "whistory-entitystatus-null";
    public static final String WHISTORY_COMMENT_RANGE_0_255 = "content-whistory-comment-range-0-255";
    public static final String WHISTORY_DIFF_RANGE_0_65535 = "content-whistory-diff-range-0-65535";
	public static final String CANNOT_REGISTER_USER = "cannot-register-user";
	public static final String USERNAME_PASSWORD_INCORRECT = "username-password-incorrect";
	public static final String WRONG_EMAIL = "common-email-wrong";
	public static final String WRONG_APIKEY = "common-api-key-wrong";
	public static final String DATE_IN_PAST = "date-must-not-be-in-the-past";
	public static final String UPLOAD_FILE_REQUIRE = "upload-file-required";
	public static final String ACCOUNT_DISABLE_TRANSACTION = "account-disable-transaction";
	public static final String ACCOUNT_INACTIVE = "account-is-inactive";
	public static final String ACCOUNT_NOT_FOUND = "account-not-found";
	public static final String ACCOUNT_BALANCE_NOT_RIGHT = "dont-enough-money";
	public static final String INVALID_REQUEST_PARAM = "request-params-invalid";
	public static final String EMPTY				= "response-data-empty";
	public static final String NOT_ACCEPTABLE = "NOT_ACCEPTABLE";
	public static final String EMAIL_INVALID = "email-invalid";
	public static final String PHONE_INVALID = "phone-invalid";
	public static final String WRONG_FORMAT = "wrong-format";
	public static final String UPLOAD_AVATAR_TYPE = "upload-avatar-must-png-or-jpg";
	public static final String DONT_HAVE_PERMISSION_ON_THAT_ITEM = "you-dont-have-permission-on-one-of-these-function";

	// CITY
	public static final String CITY_NAME_NULL = "city-name-null";
	public static final String CITY_COUNTRYID_NULL = "city-countryid-null";
	public static final String CITY_REGION_COUNTRY_COUNTRYID_NOT_EXIST = "city-region-country-countryid-not-exist";
	public static final String CITY_REGION_TIMEZONE_TIMEZONEID_NOT_EXIST = "city-region-timezone-timezoneid-not-exist";
	public static final String CITY_ORDER_NULL = "city-order-null";
	public static final String CITY_ISACTIVE_NULL = "city-isactive-null";

	// COUNTRY
	public static final String COUNTRY_COUNTRYCODE_NULL = "country-countrycode-null";
	public static final String COUNTRY_NAME_NULL = "country-name-null";
	public static final String COUNTRY_REGION_TIMEZONE_TIMEZONEID_NOT_EXIST = "country-region-timezone-timezoneid-not-exist";
	public static final String COUNTRY_ORDER_NULL = "country-order-null";
	public static final String COUNTRY_ISACTIVE_NULL = "country-isactive-null";

	// DISTRICT
	public static final String DISTRICT_NAME_NULL = "district-name-null";
	public static final String DISTRICT_CITYID_NULL = "district-cityid-null";
	public static final String DISTRICT_REGION_CITY_CITYID_NOT_EXIST = "district-region-city-cityid-not-exist";
	public static final String DISTRICT_ORDER_NULL = "district-order-null";
	public static final String DISTRICT_ISACTIVE_NULL = "district-isactive-null";

	// STREET
	public static final String STREET_NAME_NULL = "street-name-null";
	public static final String STREET_WARDID_NULL = "street-wardid-null";
	public static final String STREET_REGION_WARD_WARDID_NOT_EXIST = "street-region-ward-wardid-not-exist";
	public static final String STREET_ORDER_NULL = "street-order-null";
	public static final String STREET_ISACTIVE_NULL = "street-isactive-null";
	public static final String STREET_NOT_EXIST = "street-not-exist";

	// WARD
	public static final String WARD_NAME_NULL = "ward-name-null";
	public static final String WARD_DISTRICTID_NULL = "ward-districtid-null";
	public static final String WARD_REGION_DISTRICT_DISTRICTID_NOT_EXIST = "ward-region-district-districtid-not-exist";
	public static final String WARD_ORDER_NULL = "ward-order-null";
	public static final String WARD_ISACTIVE_NULL = "ward-isactive-null";
	public static final String WARD_NOT_EXIST = "ward-not-exist";

    //USER
    public static final String USER_ACC_NOT_FOUND = "user-account-not-found";
    public static final String USER_ACC_NULL = "user-account-null";
    public static final String USER_ACC_PASS_NULL = "user-account-password-null";
    public static final String USER_ACC_USER_NAME_NULL = "user-account-username-null";
    
    public static final String PHONE_LIMIT20 = "phone-limit-20";
    public static final String LANGUAGE_LIMIT10 = "language-limit-10";
    public static final String REGISTRANT_LIMIT50 = "registrant-limit-50";
    public static final String MODIFIER_LIMIT50 = "modifier-limit-50";
    public static final String LEVEL_LIMIT10 = "level-limit-10";
    public static final String QUESTION_TYPE_LIMIT10 = "question-type-limit-10";
    
    public static final String CODE_SUBJECT_NULL = "codesubject-null";
    public static final String USER_ACCOUNT_NULL = "useraccount-null";
    public static final String GROUP_ID_NULL = "groupid-null";
    public static final String ROOM_ID_NULL = "roomid-null";
    public static final String USER_NOT_FOUND = "user-not-found"; 
    public static final String ACCOUNT_AVAILABLE = "account-available";
    
    public static final String TOKEN_ERROR = "token-error";
    
}

