package com.sontaypham.controller.model.enums;

/**
 * Return status codes
 * The first digit indicates: 1: Product; 2: git User; 3: Transaction;
 * 4: Promotion; 5: Store; 6: Website; 7: Settings; 8: Others
 *
 * @author vantrang
 */
public enum ResultCode {

    /**
     * Success status code
     */
    SUCCESS(200, "Success"),

    /**
     * Invalid parameters
     */
    PARAMS_ERROR(4002, "Invalid parameters"),

    /**
     * Demo site restriction
     */
    DEMO_SITE_FORBIDDEN_ERROR(4001, "Demo site usage is not allowed"),

    /**
     * General server error
     */
    ERROR(400, "Server is busy, please try again later"),

    /**
     * User
     */
    USER_SESSION_EXPIRED(20004, "User session expired, please log in again"),
    USER_PERMISSION_ERROR(20005, "Insufficient permissions"),
    USER_PASSWORD_ERROR(20010, "Incorrect password"),
    USER_AUTH_ERROR(20005, "Insufficient permissions"),
    EMPLOYEE_NOT_FOUND(20027, "Employee not found"),
    EMPLOYEE_DISABLED(20031, "Employee has been disabled"),
    USER_NOT_FOUND(20002, "User not found or account has been disabled"),

    /**
     * Store
     */
    STORE_NOT_FOUND(50001, "Store not found"),
    STORE_NAME_ALREADY_EXISTS(50002, "Store name already exists"),
    STORE_ALREADY_HAS_STORE(50003, "You already own a store"),
    STORE_NOT_OPENED(50004, "This member has not opened a store yet"),
    STORE_NOT_LOGGED_IN(50005, "Not logged into the store"),
    STORE_CLOSED(50006, "Store is closed, please contact the administrator"),
    STORE_DELIVER_PRODUCT_ADDRESS(50007, "Please provide supplier delivery address"),
    FREIGHT_TEMPLATE_NOT_FOUND(50010, "Current template not found"),
    STORE_STATUS_IN_PROGRESS(50011, "Store is under registration or approval, please do not repeat the operation"),
    STORE_SHIPPING_ADDRESS_REQUIRED(50012, "Please provide shipping address"),

    /**
     * Product
     */
    PRODUCT_ERROR(11001, "Product error, please try again later"),
    PRODUCT_NOT_EXIST(11001, "Product is out of stock"),
    PRODUCT_NAME_ERROR(11002, "Invalid product name, must be 2-50 characters"),
    PRODUCT_UNDER_ERROR(11003, "Failed to take product off shelf"),
    PRODUCT_UPPER_ERROR(11004, "Failed to put product on shelf"),
    PRODUCT_AUTH_ERROR(11005, "Product approval failed"),
    POINT_PRODUCT_ERROR(11006, "Abnormal points product transaction, please try again later"),
    POINT_PRODUCT_NOT_EXIST(11020, "Points product does not exist"),
    POINT_PRODUCT_CATEGORY_EXIST(11021, "Current points product category already exists"),
    PRODUCT_SKU_SN_ERROR(11007, "Product SKU code cannot be empty"),
    PRODUCT_SKU_PRICE_ERROR(11008, "Product SKU price must be greater than 0"),
    PRODUCT_SKU_COST_ERROR(11009, "Product SKU cost must be greater than 0"),
    PRODUCT_SKU_WEIGHT_ERROR(11010, "Product weight cannot be negative"),
    PRODUCT_SKU_QUANTITY_ERROR(11011, "Product stock quantity cannot be negative"),
    PRODUCT_SKU_QUANTITY_NOT_ENOUGH(11011, "Insufficient stock quantity"),
    MUST_HAVE_PRODUCT_SKU(11012, "At least one product specification is required!"),
    MUST_HAVE_SALES_MODEL(11022, "Wholesale mode requires wholesale rules!"),

    HAVE_INVALID_SALES_MODEL(11023, "Invalid wholesale rule data (≤ 0)!"),
    MUST_HAVE_PRODUCT_SKU_VALUE(11024, "Specification value cannot be empty!"),
    DO_NOT_MATCH_WHOLESALE(11025, "Purchase quantity cannot be lower than minimum wholesale quantity!"),
    PRODUCT_NOT_ERROR(11026, "Product does not exist"),

    PRODUCT_PARAMS_ERROR(11013, "Product specification error, please refresh and try again"),
    PHYSICAL_PRODUCT_NEED_TEMP(11014, "Physical product requires a shipping template"),
    VIRTUAL_PRODUCT_NOT_NEED_TEMP(11015, "Virtual product does not require a shipping template"),
    PRODUCT_NOT_EXIST_STORE(11017, "Current user has no permission to operate this product"),
    PRODUCT_TYPE_ERROR(11016, "Product type must be selected"),
    PRODUCT_STOCK_IMPORT_ERROR(11018, "Product stock import failed, please check the data sheet"),

    /**
     * Brand
     */
    PRODUCT_BRAND_SAVE_ERROR(14001, "Failed to add brand"),
    PRODUCT_BRAND_UPDATE_ERROR(14002, "Failed to update brand"),
    PRODUCT_BRAND_DISABLE_ERROR(14003, "Failed to disable brand"),
    PRODUCT_BRAND_DELETE_ERROR(14004, "Failed to delete brand"),
    PRODUCT_BRAND_NAME_EXIST_ERROR(20002, "Brand name already exists!"),
    PRODUCT_BRAND_USE_DISABLE_ERROR(20003, "Category linked with brand, please unlink first"),
    PRODUCT_BRAND_BIND_ERROR(20005, "Brand already linked with product, please unlink first"),
    PRODUCT_BRAND_NOT_EXIST(20004, "Brand does not exist"),

    /**
     * Specification
     */
    PRODUCT_SPEC_SAVE_ERROR(13001, "Failed to save specification"),
    PRODUCT_SPEC_UPDATE_ERROR(13002, "Failed to update specification"),
    PRODUCT_SPEC_DELETE_ERROR(13003, "Category linked with this specification, please unlink first"),

    /**
     * Category
     */
    PRODUCT_CATEGORY_NOT_EXIST(10001, "Product category does not exist"),
    PRODUCT_CATEGORY_NAME_IS_EXIST(10002, "This category name already exists"),
    PRODUCT_CATEGORY_PARENT_NOT_EXIST(10003, "Parent category does not exist"),
    PRODUCT_CATEGORY_BEYOND_THREE(10004, "Up to 3 category levels allowed, addition failed"),
    PRODUCT_CATEGORY_HAS_CHILDREN(10005, "Category contains subcategories, cannot delete"),
    CATEGORY_HAS_PRODUCT(10006, "Category contains products, cannot delete"),
    PRODUCT_CATEGORY_SAVE_ERROR(10007, "Failed to save product category"),
    PRODUCT_CATEGORY_PARAMETER_NOT_EXIST(10012, "Parameter group linked to category does not exist"),
    PRODUCT_CATEGORY_PARAMETER_SAVE_ERROR(10008, "Failed to add parameter group linked to category"),
    PRODUCT_CATEGORY_PARAMETER_UPDATE_ERROR(10009, "Failed to update parameter group linked to category"),
    PRODUCT_CATEGORY_DELETE_FLAG_ERROR(10010, "Child category status cannot differ from parent category status!"),
    PRODUCT_CATEGORY_COMMISSION_RATE_ERROR(10011, "Invalid category commission rate!"),

    /**
     * Parameter
     */
    PRODUCT_PARAMETER_SAVE_ERROR(12001, "Failed to add parameter"),
    PRODUCT_PARAMETER_UPDATE_ERROR(12002, "Failed to update parameter"),

    /**
     * System exception
     */
    RATE_LIMIT_ERROR(1003, "Too many requests, please try again later"),
    ;

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
