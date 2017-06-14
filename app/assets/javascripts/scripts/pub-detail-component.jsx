import React from "react";
import axios from "axios";

import PubListItemComponent from "./pub-list-item-component.jsx";

class PubDetailComponent extends React.Component {

    constructor(props) {
        super(props);
        this.id = parseInt(props.match.params.id)
        this.state = {
        };
    }

    render = () => {
        return this.state.detail ? <div>
                {this.status()}
                <h2>{this.state.detail.pub.name}</h2>
            </div> : <div></div>;
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

    }

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