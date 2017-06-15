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
        return this.state.detail ? <div className="pub-detail">
                {this.status()}
                <h2>{this.state.detail.pub.name}</h2>
                {this.map()}
            </div> : <div></div>;
    };

    map = () => {
        const location = this.state.detail.details.location,
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
        return <Map center={position} zoom={15}>
            <TileLayer
                url='http://{s}.tile.osm.org/{z}/{x}/{y}.png'
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            />
            <Marker position={position} icon={icon}>
                <Popup>
                    <span>{this.state.detail.pub.name}</span>
                </Popup>
            </Marker>
        </Map>
    };

    visitPub = (id) => {
        if (!this.props.pub.visited) {
            axios.post(`/pubs/visit/${id}`)
                .then(this.props.refresh);
        } else {
            axios.post(`/pubs/unvisit/${id}`)
                .then(this.props.refresh);
        }
    };

    status = () => {
        if (this.props.loggedIn) {
            let className = "pub-list__list__item__status--" + (this.props.pub.visited ? "visited" : "not-visited");
            return <div className={className + " glyphicon glyphicon-ok-circle pub-list__list__item__status"}
                        onClick={e => this.visitPub(this.props.pub.id)}></div>
        }

    };

    componentDidMount = () => {
        this.refreshPubs();
    };

    refreshPubs = () => {
        axios.get(`/pubs/${this.id}`).then(response => {
            const json = response.data;
            this.setState({
                detail: json.detail,
                loggedIn: !!json.maybeEmail
            }, () => console.log(this.state));
        });
    }
}

export default PubDetailComponent;