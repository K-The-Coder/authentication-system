import { useDispatch, useSelector } from "react-redux";
import type { AppDispatch, RootState } from "./store/store";
import { useState, type FormEvent } from "react";
import { register } from "./store/authSlice";
import { Link } from "react-router";

export default function Register() {
  const dispatch = useDispatch<AppDispatch>();
  const { loading, error, authenticated } = useSelector(
    (state: RootState) => state.auth
  );

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();

    if (password != confirmPassword){
        alert("Passwords do not match");
        return;
    }
    
    dispatch(register({email, password}));
  }

  return (
    <>
      <form onSubmit={handleSubmit}>
        <h2>Register</h2>
        <input
          type="email"
          name="email"
          id="email"
          placeholder="Enter your Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          name="password"
          id="password"
          placeholder="Enter your Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          type="password"
          name="confirm-password"
          id="confirm-password"
          placeholder="Confirm Password"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        <button type="submit" disabled={loading}>{loading ? "Registering..." : "Register"}</button>
        <span>Have an account? Login <Link to="/login">here</Link></span>
        {error && <p style={{color: "red"}}>{error}!</p>}
        {authenticated && <p>Successfully registered!</p>}
      </form>
    </>
  );
}
