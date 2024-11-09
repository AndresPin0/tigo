
import { Box } from '@mui/material';
import Typography from '@mui/material/Typography';
function HomeHeader (){
return (
<Box sx={{ display: 'flex', justifyContent: 'center'}} > 
    <h2>
        <Typography variant="h2" component="h2" sx={{ color: '#f57c00', fontWeight: 'bold' }}>
            Aplanchados
        </Typography> 
    </h2> 
</Box> 
);
}
export default HomeHeader;