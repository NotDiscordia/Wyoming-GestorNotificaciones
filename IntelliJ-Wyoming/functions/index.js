/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const {onRequest} = require("firebase-functions/v2/https");
const logger = require("firebase-functions/logger");

const functions = require("firebase-functions");
const nodemailer = require("nodemailer");

// Configuración del servidor SMTP (ejemplo usando Gmail)
const mailTransport = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: "maximo.roman@uabc.edu.mx",
        pass: "MaxWell_23", // Usar una clave de aplicación si es Gmail
    },
});

// Función para enviar correos
exports.enviarCorreo = functions.https.onRequest((req, res) => {
    const destinatario = req.query.destinatario; // Obtener destinatario de la URL
    const asunto = req.query.asunto || "Prueba desde Firebase";
    const mensaje = req.query.mensaje || "Este es un correo enviado desde Firebase Cloud Functions";

    const mailOptions = {
        from: "Tu Nombre <tu-correo@gmail.com>",
        to: destinatario,
        subject: asunto,
        text: mensaje,
    };

    // Enviar el correo
    mailTransport.sendMail(mailOptions)
        .then(() => {
            res.status(200).send(`Correo enviado a: ${destinatario}`);
        })
        .catch((error) => {
            console.error("Error al enviar el correo:", error);
            res.status(500).send("Error al enviar el correo");
        });
});
