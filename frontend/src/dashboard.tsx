import { useDispatch, useSelector } from "react-redux";
import { type AppDispatch, type RootState } from "./store/store";
import { logout } from "./store/authSlice";

export default function Dashboard() {
  const dispatch = useDispatch<AppDispatch>();
  const { user } = useSelector((state: RootState) => state.auth);
  const handleLogout = () => {
    dispatch(logout());
  };
  return (
    <>
      <h1>Dashboard</h1>
      <p>Welcome, {user}</p>
      <button onClick={handleLogout}>Logout</button>
    </>
  );
}
