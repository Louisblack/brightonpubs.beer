
const iconUrl = '/assets/images/marker-icon.png',
    iconRetinaUrl = '/assets/images/marker-icon-2x.png';

export default {
    detailPageMapIcon: new L.Icon({
        iconUrl: iconUrl,
        iconRetinaUrl: iconRetinaUrl,
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        tooltipAnchor: [16, -28],
        shadowSize: [0, 0]
    }),

    listPageMapIcon: new L.Icon({
        iconUrl: iconUrl,
        iconRetinaUrl: iconRetinaUrl,
        iconSize: [12, 20],
        iconAnchor: [12, 20],
        popupAnchor: [1, -13],
        tooltipAnchor: [16, -28],
        shadowSize: [0, 0]
    })
}