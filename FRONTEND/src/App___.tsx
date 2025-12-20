import { useEffect, useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

function App() {
  const [count, setCount] = useState(0);

  async function anythingDo() {
    const response = await fetch("http://localhost:8080/api/users", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        username: "Петя Хуетя",
        avatarUrl: "https://api.dicebear.com/7.x/avataaars/svg?seed=Ivan",
      }),
    });
    const result = response.json();
    console.log(result);
  }

  console.log("Origin:", window.location.origin);
  console.log("Full URL:", window.location.href);

  useEffect(() => {
    anythingDo();
  });

  // const createUser = async () => {
  //   const userData = {
  //     username: "НовоеУникальноеИмя123", // Убедитесь, что имя уникальное
  //     email: "test@example.com",
  //     password: "password123",
  //     // другие обязательные поля
  //   };

  //   console.log("Отправляемые данные:", JSON.stringify(userData, null, 2));

  //   try {
  //     const response = await fetch("http://localhost:8080/api/users", {
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //       method: "POST",
  //       body: JSON.stringify({
  //         username: "Петя Хуетя",
  //         avatarUrl: "https://api.dicebear.com/7.x/avataaars/svg?seed=Ivan",
  //         registeredAt: "2025-12-16T12:30:45.123456",
  //       }),
  //     });
  //     console.log("Успех:");
  //   } catch (error: any) {
  //     console.error("Полная ошибка:", error);
  //     console.error("Статус ошибки:", error.response?.status);
  //     console.error("Данные ошибки:", error.response?.data);
  //     console.error("Headers ошибки:", error.response?.headers);
  //   }
  // };

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
