// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getDatabase, ref, push } from "firebase/database";
import {getAuth, FacebookAuthProvider, GoogleAuthProvider} from "firebase/auth"

const firebaseConfig = {
  apiKey: "AIzaSyCbP4BafwoO8S5HCuF16YymXdEqyFio0qg",
  authDomain: "hotrots-3a8a0.firebaseapp.com",
  projectId: "hotrots-3a8a0",
  storageBucket: "hotrots-3a8a0.appspot.com",
  messagingSenderId: "261618394302",
  appId: "1:261618394302:web:adcc434801dfb23ac46aef",
  measurementId: "G-MFHE9TYHHN"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const database = getDatabase(app);
const auth = getAuth(app);
const provider= new FacebookAuthProvider();
const providerGG=new GoogleAuthProvider();

export { auth, provider, providerGG, database, ref, push};
