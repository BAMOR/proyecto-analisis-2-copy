// src/reducer/itemsReducer.js

import { AddProductCart } from "./itemAction";
import { UpdateQuantityProductCart } from "./itemAction";
import { DeleteProductCart } from "./itemAction";
export const itemsReducer = (state = [], action) => {
  switch (action.type) {
    case 'SET_CART_FROM_BACKEND':
      return action.payload; // Reemplaza el estado con los datos de la BD
    case AddProductCart:
      // Si el producto ya existe, actualiza la cantidad
      const hasItem = state.find((i) => i.product.id === action.payload.product.id);
      if (hasItem) {
        return state.map((i) => {
          if (i.product.id === action.payload.product.id) {
            return {
              ...i,
              quantity: i.quantity + 1,
            };
          }
          return i;
        });
      }
      // Si no existe, añádelo
      return [
        ...state,
        {
          product: action.payload.product,
          quantity: 1,
          id: action.payload.id
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
      return state.filter((i) => i.id !== action.payload);
    default:
      return state;
  }
};