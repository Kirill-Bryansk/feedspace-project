import { useState } from "react";
import style from "./usersCards.module.scss";

export const AdderUsersCards = () => {
  const [valueName, setValueName] = useState<string>("");
  const [valueAvatar, setValueAvatar] = useState<string>("");

  const hundleSubmit = async () => {
    console.log("Начало отправки");

    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          username: valueName,
          avatarUrl: valueAvatar,
        }),
      });

      console.log("Response status: ", response.status);

      setValueName('');
      setValueAvatar('');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={style.wrapper}>
      <button onClick={hundleSubmit}>Add User</button>
      <input
        type="text"
        className={style.valuesName}
        value={valueName}
        onChange={(e) => setValueName(e.currentTarget.value)}
        placeholder="Имя"
      />
      <input
        type="text"
        className={style.valuesAvatar}
        value={valueAvatar}
        onChange={(e) => setValueAvatar(e.currentTarget.value)}
        placeholder="Ссылка на аватар"
      />
    </div>
  );
};
