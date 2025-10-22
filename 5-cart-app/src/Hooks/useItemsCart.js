// src/Hooks/useItemsCart.js
import { useEffect, useReducer } from "react";
import { fetchCartFromBackend, addToCartBackend, deleteFromCartBackend } from "../services/cartService";
import { itemsReducer } from "../reducer/itemsReducer";
import { AddProductCart, DeleteProductCart } from "../reducer/itemAction";
import { processPayment } from "../services/paymentService";


// Estado inicial vacío
const initialCartItems = [];

export const useItemCart = () => {
  const [cartItems, dispatch] = useReducer(itemsReducer, initialCartItems);

  // Cargar carrito desde la BD al iniciar
  useEffect(() => {
    const loadCart = async () => {
      try {
        const items = await fetchCartFromBackend();
        // Restablecer el estado con los datos de la BD
        dispatch({
          type: 'SET_CART_FROM_BACKEND',
          payload: items
        });
      } catch (error) {
        console.error('No se pudo cargar el carrito:', error);
      }
    };
    loadCart();
  }, []);

  const handlerAddProductCart = async (product) => {
    try {
      const newItem = await addToCartBackend(product);
      // Agregar al estado local
      dispatch({
        type: AddProductCart,
        payload: {
          product,
          id: newItem.id, // ID del ítem en la BD
          quantity: 1
        }
      });
    } catch (error) {
      console.error('Error al agregar producto:', error);
    }
  };

  const handlerDeleteProduct = async (itemId) => {
    try {
      await deleteFromCartBackend(itemId);
      dispatch({
        type: DeleteProductCart,
        payload: itemId
      });
    } catch (error) {
      console.error('Error al eliminar producto:', error);
    }
  };

    const processCheckout = async (paymentMethod) => {
    try {
      const message = await processPayment(paymentMethod);
      // Vaciar carrito local después del pago exitoso
      dispatch({
        type: 'CLEAR_CART'
      });
      return message;
    } catch (error) {
      throw error;
    }
  };


  return {
    cartItems,
    handlerAddProductCart,
    handlerDeleteProduct,
     processCheckout 
  };


  

};

