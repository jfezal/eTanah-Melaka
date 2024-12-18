<%-- 
    Document   : kemasukan_hakmilik_asal
    Created on : 03 November 2009, 11:04:49 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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

            window.open("pihak?pihakDetail&idpihak="+$("#hakmilik").val(), "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });
         $("#popuptambahpihak").click( function() {
            window.open("pihakBerkepentingan", "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#add").click( function(){

            frm = this.form;
            //TODO :
            var len = $(".nokp1").length;


            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var id = $(".idpihak"+i).text();
                    var nama = $(".nama"+i).text();
                    var nokp = $(".nokp"+i).text();
                    var tablename = "linepihak";
                   <%-- alert("nama :"+nama);
                    alert("nokp"+nokp);--%>
                    self.opener.addRows(nama , nokp,tablename);

                }
            }
            self.close();
        });
    });
</script>
<s:form beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>Hakmilik Asal</legend>
        <p><label>ID Hakmilik Asal : </label>
        <s:text name="" />
        </p>
        <p><label>Tarikh Mula dimiliki : </label>
        <s:text name="" />
        </p>
        <p><label>&nbsp;</label>
             <s:button name="popup" id="popup" value="Simpan" class="btn"/>
        </p>
       
    </fieldset>
</div>
</s:form>