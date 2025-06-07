package resources;

public enum APIResources {

    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    delPlaceAPI("/maps/api/place/delete/json"),

    createBookAPI("/Library/Addbook.php"),
    getBook("/Library/GetBook.php"),
    deleteBook(":/Library/DeleteBook.php"),

    placeOrderAPI("/store/order"),
    getOrderAPI("/store/order/{id}");


    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}