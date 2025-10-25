

export const getProducts = async() => {

    const response = await fetch('http://localhost:8080/cartapp/products')
    const products = response.json();
    return products;
}

export const calculateTotal = (items) => {
    return items.reduce(
        (acc, item) => acc + (item.product.price * item.quantity), 
        0);

}