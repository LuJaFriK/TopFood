
# ☕ TopFood

Este programa de escritorio, desarrollado en Java y operado desde la línea de comandos (CLI), permite a una cafetería gestionar de forma eficiente sus mesas, pedidos y productos, todo almacenado en archivos locales.

## ✅ Funcionalidades

### 🔐 Gestión de Meseros
- Registro y eliminación de meseros.
- Inicio de sesión mediante **código de mesero** y **contraseña**.

### 🍽️ Gestión de Mesas
- Cada mesa tiene:
  - Un número distintivo.
  - Un mesero asignado.
  - Estado de cuenta (solicitada o no).
  - Total del consumo.
  - Registro de alimentos ordenados.
- Funciones:
  - Tomar pedidos.
  - Unir cuentas entre mesas.
  - Imprimir cuenta.
  - Eliminar cuenta para reutilizar la mesa.

### 🥪 Registro de Alimentos
- Cada alimento incluye:
  - Nombre.
  - Precio base.
  - Comentario opcional (ej. sin azúcar, poco picante).
  - Indicador de disponibilidad.

#### ☕ Café
- Parámetros personalizados:
  - Con o sin cafeína.
  - Caliente o con hielo.
  - Tipo de leche (normal o almendra).
  - Tamaño: chico, mediano (+10%), grande (+20%).
- Si se elige leche de almendra, se suma un costo adicional.

#### 🍰 Snacks y Postres
- Tamaños disponibles:
  - Porción individual.
  - Porción completa.
  - Paquete.
- Para paquetes o versiones completas:
  - Precio = (N° de porciones individuales × precio unitario) - (2 × precio unitario).

### 📋 Administración del Menú
- Agregar nuevos alimentos.
- Modificar disponibilidad (alta o baja).

## 🧾 Ejemplo de Cálculo de Precio

**Café grande con leche de almendra (precio base $35, leche almendra +$6):**  
- Aumento por tamaño grande: +20% = +$7  
- Precio total: $35 + $7 + $6 = **$48**

**Caja de 10 galletas a $5 cada una:**  
- Precio sin descuento: $50  
- Descuento equivalente a 2 galletas: -$10  
- Precio final: **$40**

## 💾 Almacenamiento

El sistema guarda la información de meseros, alimentos, mesas y pedidos en **archivos locales**. No se requiere conexión a internet ni base de datos externa.

## 🧑‍💼 Seguridad

- El acceso está protegido por contraseña por mesero.
- Solo los meseros registrados pueden operar el sistema.

## 🚧 Estado del Proyecto

**En desarrollo**  
Se planea mejorar la interfaz y agregar validaciones adicionales.
