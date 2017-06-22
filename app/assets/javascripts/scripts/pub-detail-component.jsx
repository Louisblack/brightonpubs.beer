import React from "react";
import axios from "axios";

import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import mapIcons from "./map-icons.jsx";
import OsmTileLayer from "./osm-tile-layer.jsx";

class PubDetailComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = parseInt(props.match.params.id);
        this.state = {
        };
    }

    render = () => {
        return this.state.pub ? <div className="pub-detail">
                <h2>{this.state.pub.details.name}</h2>
                <div className="container">
                    {this.state.pub.details.imgUrl ? <img src={this.state.pub.details.imgUrl} className="pub-detail__image col-md-6"/> : null}
                    {this.map()}
                </div>
            </div> : <div></div>;
    };

    map = () => {
        const location = this.state.pub.location,
              position = [location.lat, location.lng];

        return <Map center={position} zoom={15} className="col-md-6">
            <OsmTileLayer />
            <Marker position={position} icon={mapIcons.detailPageMapIcon}>
                <Popup>
                    <span>{this.state.pub.details.name}</span>
                </Popup>
            </Marker>
        </Map>
    };

    componentDidMount = () => {
        this.refreshPubs();
    };

    refreshPubs = () => {
        axios.get(`/pubs/${this.id}`).then(response => {
            const json = response.data;
            this.setState({
                pub: json.detail,
                loggedIn: !!json.maybeEmail
            }, () => console.log(this.state));
        });
    }
}

export default PubDetailComponent;