
import { useItemCart } from "./Hooks/useItemsCart"
import { Navbar } from "./components/Navbar"
import { CartRouters } from "./routes/CartRoutes"







export const CartApp = () => {

    const { cartItems, handlerAddProductCart, handlerDeleteProduct } = useItemCart()




    return (
        <>
        <Navbar/>
            <div className="container my-4">
                <h3>Cart App</h3>
                
                <CartRouters cartItems ={cartItems}
                    handlerAddProductCart = {handlerAddProductCart}
                    handlerDeleteProduct = {handlerDeleteProduct}

                />

            </div>
        </>
    )
}