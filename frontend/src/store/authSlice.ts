import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { checkSession, loginUser, logoutUser, registerUser } from "../api/auth";
import type {
  AuthState,
  SessionResponse,
  UserCredentials,
} from "../interfaces";

const initialState: AuthState = {
  authenticated: false,
  user: null,
  loading: false,
  error: null,
};

export const register = createAsyncThunk(
  "auth/register",
  async (
    { email, password }: UserCredentials,
    { rejectWithValue, dispatch }
  ) => {
    try {
      const response = await registerUser(email, password);
      await dispatch(verifySession());
      return response;
    } catch (err: any) {
      return rejectWithValue(
        err.response?.data || { message: "Registration Failed" }
      );
    }
  }
);

export const login = createAsyncThunk(
  "auth/login",
  async (
    { email, password }: UserCredentials,
    { rejectWithValue, dispatch }
  ) => {
    try {
      const response = await loginUser(email, password);
      await dispatch(verifySession());
      return response;
    } catch (err: any) {
      return rejectWithValue(err.response?.data || { message: "Login Failed" });
    }
  }
);

export const logout = createAsyncThunk("auth/logout", async () => {
  await logoutUser();
  return;
});

export const verifySession = createAsyncThunk(
  "auth/verifySession",
  async (_, thunkAPI) => {
    try {
      const response = await checkSession();
      return response;
    } catch {
      return thunkAPI.rejectWithValue({ authenticated: false });
    }
  }
);

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    clearError: (state) =>{
      state.error = null;
    }
  },
  extraReducers: (builder) => {
    builder
      //for registration
      .addCase(register.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(register.fulfilled, (state, action) => {
        state.loading = false;
        state.authenticated = true;
        state.user = action.payload.email || null;
      })
      .addCase(register.rejected, (state, action) => {
        state.loading = false;
        state.error = (action.payload as {message?: string})?.message || "Registration Failed";
      })

      //for login
      .addCase(login.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(login.fulfilled, (state, action) => {
        state.loading = false;
        state.authenticated = true;
        state.user = action.payload.email || null;
      })
      .addCase(login.rejected, (state, action) => {
        state.loading = false;
        state.error =
          (action.payload as { message?: string })?.message || "Login Failed!";
      })

      //for logout
      .addCase(logout.fulfilled, (state) => {
        state.authenticated = false;
        state.user = null;
      })

      //for verifying session
      .addCase(verifySession.pending, (state) => {
        state.loading = true;
      })
      .addCase(verifySession.fulfilled, (state, action) => {
        state.loading = false;
        const data = action.payload as SessionResponse;
        state.authenticated = data.authenticated;
        state.user = data.email || null;
      })
      .addCase(verifySession.rejected, (state) => {
        state.loading = false;
        state.authenticated = false;
        state.user = null;
      });
  },
});

export const {clearError} = authSlice.actions;
export default authSlice.reducer;
