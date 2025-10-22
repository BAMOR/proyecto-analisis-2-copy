// src/reducer/itemsReducer.js
import { AddProductCart, DeleteProductCart, UpdateQuantityProductCart } from "./itemAction";

export const itemsReducer = (state = [], action) => {
  switch (action.type) {
    case 'SET_CART_FROM_BACKEND':
      return action.payload; // Reemplaza todo el estado

    case AddProductCart:
      return [
        ...state,
        {
          product: action.payload.product,
          quantity: action.payload.quantity || 1,
          id: action.payload.id // ID del ítem en la BD
        }
      ];

    case UpdateQuantityProductCart:
      return state.map((i) => {
        if (i.product.id === action.payload.id) {
          return {
            ...i,
            quantity: i.quantity + 1,
          };
        }
        return i;
      });

    case DeleteProductCart:
      return state.filter((i) => i.id !== action.payload); // Filtra por ID del ítem

      case 'CLEAR_CART': // ← Nuevo caso
      return [];

    default:
      return state;
  }
};