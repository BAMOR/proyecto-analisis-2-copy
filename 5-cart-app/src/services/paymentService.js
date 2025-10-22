// src/services/paymentService.js
const API_URL = 'http://localhost:8080';

export const processPayment = async (paymentMethod) => {
  const response = await fetch(`${API_URL}/cart/checkout?paymentMethod=${paymentMethod}&userId=default_user`, {
    method: 'POST'
  });
  
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || 'Error al procesar el pago');
  }
  
  return response.text(); // Retorna el mensaje de éxito
};