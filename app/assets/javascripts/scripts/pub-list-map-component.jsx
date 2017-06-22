import React from "react";
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import {withRouter} from "react-router-dom";

import mapIcons from "./map-icons.jsx";
import OsmTileLayer from "./osm-tile-layer.jsx";

class PubListItemComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    };

    render = () => {
        const centre = [50.8305877,-0.1376037];

        return <Map center={centre} zoom={13} className="pub-list__map">
            <OsmTileLayer />
            {this.props.pubs.filter(pub => {return pub.location}).map(pub => {
                const position = [pub.location.lat, pub.location.lng];
                return <Marker position={position} icon={mapIcons.listPageMapIcon}>
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