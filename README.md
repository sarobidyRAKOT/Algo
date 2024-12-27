ETAPE A SUIVRE
1 - dossier lib du projet:
wildfly-33.0.1.Final\modules\system\layers\base\jakarta\servlet\
    - api
    - jsp
    - jstl
    -> alaina daoly .java ao anatiny reo dossier reo (any am farany reo no misy an ilay .jar) dia atao ao anaty /lib
    - aveo miditra ao am dossier projet-build dia sokafana ilay fichier "build-impl.xml" de solona ilay value am ito ligne ito
    <property name="serveurAPP.dir" value="C:/WILDFLY/wildfly-33.0.1.Final"/>
    soloina chemin makao amin'ny wildfly

2 - Creation user dans wildfly
    - dossier bin : mis add-user.bat
    - lancer 
    - manome choix izy dia ilay "application user" no safidiana (dia mangataka username sy mdp izy aveo)

3 - lancer wildfly 
4 - compilation et deploiement du projet 
    - cmd ao am projet 
    - tapez "ant" et entrer
    - a la fin tokony misy hoe 'build faild' na "build succes"
5 - ra "build succes"
    - miditra navigateur elah dia mitapez an ito url ito "http://127.0.0.1:9990/console/index.html"
    - misy resaka deploiement zay ao de (cliquer start) 
    - dia tokony ho itan lah eo am sisiny havia eo we boulangerie.war de cliquez lah io
    - mis context root   /boulangerie zay miseho eo am ilany de ilay boulangerie lien azo cliquena
    - dia ra mande ilay page de made zan ialy izy
      