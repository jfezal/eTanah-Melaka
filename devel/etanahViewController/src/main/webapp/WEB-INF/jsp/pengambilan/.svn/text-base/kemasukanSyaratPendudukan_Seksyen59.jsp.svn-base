<%--
    Document   : kemasukanSyaratPendudukan_Seksyen59
    Created on : May 14, 2010, 1:57:48 PM
    Author     : massita
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
 function addRow(tableID1) {

       document.getElementById("rowCount").value = 1;
       var table = document.getElementById(tableID1);

       var rowCount = table.rows.length;
       var row = table.insertRow(rowCount);

       var cell2 = row.insertCell(0);
       cell2.innerHTML = "<b>"+(rowCount+1)+"</b>";

       var cell1 = row.insertCell(1);
       var element1 = document.createElement("textarea");
       element1.t = "text"+(rowCount+1);
       element1.rows = 3;
       element1.cols = 80;
       element1.name ="text"+(rowCount+1);
       element1.id ="text"+(rowCount+1);
       cell1.appendChild(element1);
       document.getElementById("rowCount").value=rowCount +1;
      <%-- var msg = "Masuk";
       alert(msg);--%>
   }

      function delRow(tableid) {
       var table = document.getElementById(tableid);
       var rowCount = table.rows.length;
       table.deleteRow(rowCount-1);
   }

   function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function hakmilikDetails(idHakmilik) {
        $.post('${pageContext.request.contextPath}/pengambilan/syaratPendudukan?hakmilikDetails&idHakmilik='+idHakmilik,
                function(data){
                    $('#page_div').html(data);
                }, 'html');

            }
</script>

    <s:form beanclass="etanah.view.stripes.pengambilan.KemasukanSyaratPendudukanActionBean" id="form1">
        <s:messages />
        <s:errors/>
        <div  id="hakmilik_details">
            <fieldset class="aras1">
                <legend>
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/syaratPendudukan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.KemasukanSyaratPendudukanActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                            <%--<a href="javascript:hakmilikDetails('${line.hakmilik.idHakmilik}');" >
                                               ${line.hakmilik.idHakmilik}
                                           </a>--%>
                        </display:column>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </p>
                <br/><br/>
            </fieldset>
            <c:if test="${actionBean.hakmilik ne null}">
                <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Sebab Pendudukan Sementara</legend>
                    <s:hidden name="rowCount" id="rowCount" value="0"></s:hidden>
                    <table  id ="dataTable1" align="center">
                         <tr>
                            <td><b></b></td>
                            <td><s:textarea name="sebabPendudukan" id="sebabPendudukan" rows="5" cols="100"></s:textarea></td>
                         </tr>
                     </table>
                </fieldset><br />
                 <p align="center">
                     <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                    <s:button name="simpan" id="" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
                    </div>
                    <br />
            </c:if>
        </div>
</s:form>