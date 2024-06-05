package com.saucedemo.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {
    WebDriver driver;

    @FindBy(css = ".inventory_item")
    List<WebElement> products;

    @FindBy(css = ".shopping_cart_link")
    WebElement cartLink;

    @FindBy(css = ".title")
    WebElement title;

    @FindBy(css = ".product_sort_container")
    WebElement sortDropdown;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProducts() {
        return products;
    }

    public WebElement getProductByName(String productName) {
        return products.stream().filter(
                product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(By.cssSelector("[id*='add-to-cart']")).click();
    }

    public void openCart() {
        cartLink.click();
    }

    public String getTitle() {
        return title.getText();
    }

    public void sortProducts(String sortBy) {
        sortDropdown.sendKeys(sortBy);
    }

    public List<String> getProductPrices() {
        return products.stream()
                .map(product -> product.findElement(By.cssSelector(".inventory_item_price")).getText())
                .collect(Collectors.toList());
    }

    public void viewProductDetails(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(By.cssSelector(".inventory_item_name")).click();
    }
}
