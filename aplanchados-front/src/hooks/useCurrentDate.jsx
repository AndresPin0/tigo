import { useState, useEffect } from 'react';

/**
 * Hook personalizado para obtener y gestionar la fecha actual
 * en el formato compatible con `datetime-local` en la zona horaria de Colombia (UTC -5).
 */
export const useCurrentDate = () => {
    const [currentDate, setCurrentDate] = useState('');

    useEffect(() => {
        const now = new Date();

        // Ajustar la hora para la zona horaria de Colombia (UTC -5)
        const colombiaOffset = -5; // Colombia está en UTC -5
        const currentDateInColombia = new Date(now.setHours(now.getHours() + colombiaOffset));

        const formattedDate = currentDateInColombia.toISOString().slice(0, 16); 
        setCurrentDate(formattedDate);
    }, []);

    return [currentDate, setCurrentDate];
};
