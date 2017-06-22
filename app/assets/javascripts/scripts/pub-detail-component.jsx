import React from "react";
import axios from "axios";

import { Map, Marker, Popup, TileLayer } from 'react-leaflet';

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

        const icon = new L.Icon({
            iconUrl: '/assets/images/marker-icon.png',
            iconRetinaUrl: '/assets/images/marker-icon-2x.png',
            iconSize: [25, 41],
            iconAnchor: [12, 41],
            popupAnchor: [1, -34],
            tooltipAnchor: [16, -28],
            shadowSize: [0, 0]
        });
        return <Map center={position} zoom={15} className="col-md-6">
            <TileLayer
                url='http://{s}.tile.osm.org/{z}/{x}/{y}.png'
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            />
            <Marker position={position} icon={icon}>
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