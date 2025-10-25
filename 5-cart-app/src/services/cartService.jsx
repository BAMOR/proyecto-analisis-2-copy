// src/services/cartService.js
const API_URL = 'http://localhost:8080/cartapp';

// Obtener carrito desde la BD
export const fetchCartFromBackend = async () => {
  const response = await fetch(`${API_URL}/cart`);
  if (!response.ok) throw new Error('Error al cargar el carrito');
  const items = await response.json();
  // Transformamos la respuesta del backend al formato que usa tu frontend
  return items.map(item => ({
    product: item.product,
    quantity: item.quantity,
    id: item.id  // Guardamos el ID del ítem del carrito
  }));
};

// Agregar producto al carrito (BD)
export const addToCartBackend = async (product) => {
  const response = await fetch(`${API_URL}/cart/add?productId=${product.id}&quantity=1`, {
    method: 'POST'
  });
  if (!response.ok) throw new Error('Error al agregar al carrito');
  return response.json();
};

// Eliminar ítem del carrito (BD)
export const deleteFromCartBackend = async (itemId) => {
  const response = await fetch(`${API_URL}/cart/item/${itemId}`, {
    method: 'DELETE'
  });
  if (!response.ok) throw new Error('Error al eliminar del carrito');
};