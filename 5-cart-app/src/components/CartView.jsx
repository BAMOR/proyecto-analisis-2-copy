// src/components/CartView.js
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { calculateTotal } from "../services/productService"; // Asegúrate que esta ruta es correcta

export const CartView = ({items, handlerDelete, processCheckout}) => {
  const [total, setTotal] = useState(0);
  const [paymentMethod, setPaymentMethod] = useState('efectivo');
  const [paymentMessage, setPaymentMessage] = useState('');
  const [isProcessing, setIsProcessing] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setTotal(calculateTotal(items));
  }, [items]);

  const handlePayment = async (e) => {
    e.preventDefault();
    setIsProcessing(true);
    setPaymentMessage('');
    
    try {
      const message = await processCheckout(paymentMethod);
      setPaymentMessage(message);
      setTimeout(() => navigate('/catalog'), 2000);
    } catch (error) {
      setPaymentMessage('Error: ' + error.message);
    } finally {
      setIsProcessing(false);
    }
  };

  const onCatalog = () => {
    navigate('/catalog');
  };

  if (items.length === 0) {
    return (
      <div className="alert alert-warning">No hay productos en el carrito</div>
    );
  }

  return (
    <>
      <h3>Carro de Compras</h3>
      <table className="table table-hover table-striped">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Total</th>
            <th>Eliminar</th>
          </tr>
        </thead>
        <tbody>
          {items.map(item => (
            <tr key={item.id}>
              <td>{item.product.name}</td>
              <td>{item.product.price}</td>
              <td>{item.quantity}</td>
              <td>{item.quantity * item.product.price}</td>
              <td>
                <button 
                  className="btn btn-danger btn-sm"
                  onClick={() => handlerDelete(item.id)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
        <tfoot>
          <tr>
            <td colSpan="3" className="text-end fw-bold">Total</td>
            <td colSpan="2" className="text-start fw-bold">${total}</td>
          </tr>
        </tfoot>
      </table>

      <div className="mt-4 p-3 bg-light rounded">
        <h4>Finalizar Compra</h4>
        <form onSubmit={handlePayment}>
          <div className="mb-3">
            <label className="form-label">Método de Pago:</label>
            <select 
              className="form-select"
              value={paymentMethod}
              onChange={(e) => setPaymentMethod(e.target.value)}
              disabled={isProcessing}
            >
              <option value="efectivo">Efectivo</option>
              <option value="tarjetacredito">Tarjeta de Crédito</option>
              <option value="paypal">PayPal</option>
            </select>
          </div>
          <button 
            type="submit" 
            className="btn btn-success"
            disabled={isProcessing}
          >
            {isProcessing ? 'Procesando...' : 'Finalizar Compra'}
          </button>
        </form>
        
        {paymentMessage && (
          <div className={`alert mt-3 ${paymentMessage.includes('Error') ? 'alert-danger' : 'alert-success'}`}>
            {paymentMessage}
          </div>
        )}
      </div>

      <button className="btn btn-primary mt-3" onClick={onCatalog}>
        Seguir Comprando
      </button>
    </>
  );
};