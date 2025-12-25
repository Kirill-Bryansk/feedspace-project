import { useEffect, useState } from "react";
import style from "./arrayWithCards.module.scss";
import { UsersCard } from "../UsersCard/AdderUsersCard";
import type { UsersCardType } from "../interface/interface";

export const ArrayWithCards = () => {
  const [isNewData, setIsNewData] = useState<boolean>(false);
  const [newArr, setNewArr] = useState<UsersCardType[]>();
  console.log("Начало получения");

  useEffect(() => {
    const getUsers = async (): Promise<void> => {
      try {
        const response = await fetch("http://localhost:8080/api/users");
        const data = await response.json();
        setNewArr(data);
      } catch (error) {
        console.error(error);
      }
    };

    getUsers();
    console.log("Конец получения");
  }, [isNewData]);

  return (
    <div className={style.wrapperMain}>
      <button onClick={() => setIsNewData(!isNewData)} className={style.showBtn}>Show Users</button>
      <div className={style.wrapperArr}>
        {newArr?.map((item) => {
          console.log(newArr);
          return (
            <UsersCard username={item.username} avatarUrl={item.avatarUrl} />
          );
        })}
      </div>
    </div>
  );
};
