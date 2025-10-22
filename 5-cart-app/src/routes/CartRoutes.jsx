// src/routes/CartRouters.js
import { Navigate, Route, Routes } from "react-router-dom"
import { CatalogView } from "../components/CatalogView"
import { CartView } from "../components/CartView"

export const CartRouters = ({ 
  handlerAddProductCart, 
  handlerDeleteProduct, 
  cartItems,
  processCheckout // ✅ Añade processCheckout a las props
}) => {
  return (
    <Routes>
      <Route
        path="catalog"
        element={<CatalogView handler={handlerAddProductCart} />} 
      />
      <Route 
        path="cart" 
        element={
          cartItems?.length <= 0 ? (
            <div className="alert alert-warning">No hay productos en el carrito</div>
          ) : (
            <div className="my-4 w-50">
              <CartView 
                items={cartItems} 
                handlerDelete={handlerDeleteProduct}
                processCheckout={processCheckout} // ✅ Pasa la prop
              />
            </div>
          )
        } 
      />
      <Route path="/" element={<Navigate to={'/catalog'} />} />
    </Routes>
  );
};