import React from "react";
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';
import {withRouter} from "react-router-dom";

import mapIcons from "./map-icons.jsx";

class OsmTileLayer extends React.Component {

    constructor(props) {
        super(props);
    };

    render = () => {
        return <TileLayer
            url='http://{s}.tile.osm.org/{z}/{x}/{y}.png'
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        />
    };
}

export default OsmTileLayer;