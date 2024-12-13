<%-- 
    Document   : carian_hakmilik
    Created on : 15-Oct-2009, 10:22:28
    Author     : md.nurfikri
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {        
        $(".popup").click( function() {            
            
            window.open("hakmilik?hakmilikDetail&idHakmilik="+$("#hakmilik").val(), "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#add").click( function(){
            frm = this.form;
            //TODO :
            var len = $(".daerah1").length;
                    
            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var id = $(".idhakmilik"+i).text();
                    var caw = $(".cawangan"+i).text();
                    var daerah = $(".daerah"+i).text();
                    var lot = $(".lot"+i).text();
                    var luas = $(".luas"+i).text();
                    var noLitho = $(".noLitho"+i).text();
                    self.opener.addRows(id, caw, daerah, lot, luas, noLitho);
                }
            }
            self.close();
        });
    });
</script>
    <s:form beanclass="etanah.view.etapp.TerimaBorangKActionBean">
       <fieldset class="aras1">
        <legend>
            Maklumat Permohonan
        </legend>
        <p>
            <label for="Id Permohonan">Id Permohonan  :</label>
            id Permohonan 
        </p>
    </fieldset>
    <fieldset class="aras1">
        <legend>
            Maklumat Warta
        </legend>
        <p>
            <label for="Tarikh Warta">Tarikh Warta :</label>
            id Permohonan 
        </p>
        <p>
            <label for="No Warta">No Warta :</label>
            id Permohonan 
        </p>
    </fieldset>
    <br>
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik
        </legend>

        <div align="center">
            <p> 
                <s:submit name="selesai" value="Selesai" class="btn"/>
            </p>
        </div>            
    </fieldset>
    </s:form>

