import { createContext, useContext } from "react";

interface Context {
    // Define your context properties and methods here
    // getUser: () => User | null;
    // setUser: (user: User) => void;
}

const context = createContext<Context | null>(null);

export default function ContextProvider({ children }: { children: React.ReactNode }) {

    //Implement functions
    // const getUser = () => {
    //     return user
    // }
    // const setUser = (user: User) => {
    //     setUser(user)
    // }

    const ContextValues = {
        // getUser
        // setUser
    }
    return (
        <context.Provider value={ContextValues}>
            {children}
        </context.Provider>
    )
}

export const ContextConsumer = () => {
    const contextValue = useContext(context);
    if (!contextValue) {
        throw new Error("ContextConsumer must be used within a ContextProvider");
    }
    return contextValue;
}