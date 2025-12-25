import style from "./adderUserCard.module.scss";
import type { UsersCardType } from "../interface/interface";

export const UsersCard = (props: UsersCardType) => {
  return (
    <>
      <div className={style.wrapper}>
        <div className={style.upPart}>
          <img className={style.avatarImg} src={props.avatarUrl} alt="" />
        </div>
        <div className={style.downPart}>
          <p className={style.usersName}>{props.username}</p>
        </div>
      </div>
    </>
  );
};
