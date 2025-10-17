import { useDispatch } from "react-redux";
import type { AppDispatch } from "./store/store";
import { useLocation } from "react-router";
import { useEffect, useRef } from "react";
import { clearError } from "./store/authSlice";

export default function AuthRouteWatcher() {
  const dispatch = useDispatch<AppDispatch>();
  const location = useLocation();
  const firstRender = useRef(true);

  useEffect(() => {
    if (firstRender.current) {
      firstRender.current = false;
      return;
    }
    dispatch(clearError());
  }, [location.pathname, dispatch]);

  return null;
}
