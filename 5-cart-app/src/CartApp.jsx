// src/CartApp.jsx
import { useItemCart } from "./Hooks/useItemsCart"
import { Navbar } from "./components/Navbar"
import { CartRouters } from "./routes/CartRoutes"

export const CartApp = () => {
  // ✅ Añade processCheckout a la desestructuración
  const { 
    cartItems, 
    handlerAddProductCart, 
    handlerDeleteProduct, 
    processCheckout 
  } = useItemCart();

  return (
    <>
      <Navbar/>
      <div className="container my-4">
        <h3>Cart App</h3>
        <CartRouters 
          cartItems={cartItems}
          handlerAddProductCart={handlerAddProductCart}
          handlerDeleteProduct={handlerDeleteProduct}
          processCheckout={processCheckout} // ✅ Ahora sí está definido
        />
      </div>
    </>
  );
};