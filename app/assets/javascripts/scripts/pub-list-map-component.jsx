import React from "react";
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import {withRouter} from "react-router-dom";
class PubListItemComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    };

    render = () => {
        const centre = [50.8305877,-0.1376037];
        const icon = new L.Icon({
            iconUrl: '/assets/images/marker-icon.png',
            iconRetinaUrl: '/assets/images/marker-icon-2x.png',
            iconSize: [12, 20],
            iconAnchor: [12, 20],
            popupAnchor: [1, -13],
            tooltipAnchor: [16, -28],
            shadowSize: [0, 0]
        });

        return <Map center={centre} zoom={13} className="pub-list__map">
            <TileLayer
                url='http://{s}.tile.osm.org/{z}/{x}/{y}.png'
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            />
            {this.props.pubs.filter(pub => {return pub.location}).map(pub => {
                const position = [pub.location.lat, pub.location.lng];
                return <Marker position={position} icon={icon}>
                    <Popup>
                        <span><button onClick={() => this.goToPub(pub.details.id)}>{pub.details.name}</button></span>
                    </Popup>
                </Marker>
            })}
        </Map>;
    };

    goToPub = (id) => {
        console.log(id);
        this.props.history.push(`/pub/${id}`);
    }
}

export default withRouter(PubListItemComponent);