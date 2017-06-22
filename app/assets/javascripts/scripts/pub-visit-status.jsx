import React from "react";

import axios from "axios";

class PubVisitStatus extends React.Component {

    constructor(props) {
        super(props);
    };

    render = () => {
        return <div>
            {this.status()}
        </div>
    };

    status = () => {
        if (this.props.loggedIn) {
            let className = "pub-list__list__item__status--" + (this.props.pub.visited ? "visited" : "not-visited");
            return <div className={className + " glyphicon glyphicon-ok-circle pub-list__list__item__status"}
                        onClick={e => this.visitPub(this.props.pub.id).then(this.props.refresh)}></div>
        }
    };

    visitPub = (id) => {
        if (!this.props.pub.visited) {
            return axios.post(`/pubs/visit/${id}`);
        } else {
            return axios.post(`/pubs/unvisit/${id}`);
        }
    };

}

export default PubVisitStatus;