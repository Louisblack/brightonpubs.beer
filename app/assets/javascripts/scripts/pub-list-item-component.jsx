import React from 'react';
import axios from 'axios';

class PubListItemComponent extends React.Component {

    render = () => {
        return <li key={this.props.pub.id} className="pub-list__item">
            <div>
                {this.status()}
                <h3>{this.props.pub.name}</h3>
            </div>

        </li>;
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
            let className = "pub-list__item__status--" + (this.props.pub.visited ? "visited" : "not-visited");
            return <div className={className + " glyphicon glyphicon-ok-circle pub-list__item__status"}
                        onClick={e => this.visitPub(this.props.pub.id)}></div>
        }

    }

}

export default PubListItemComponent;