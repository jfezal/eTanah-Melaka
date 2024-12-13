<%-- 
    Document   : RekodBayaranPampasanTambahan
    Created on : Sep 29, 2010, 9:47:14 AM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){


$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
});

function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RekodBayaranPampasanTambahanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">REKOD PENERIMAAN BAYARAN PAMPASAN TAMBAHAN </font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>

<fieldset class="aras1"><br/>
    <div align="center">
    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                   requestURI="/pengambilan/bayaranPampasanTambahan" id="line">
        <display:column title="No" sortable="true">${line_rowNum}</display:column>
        <display:column property="hakmilik.noHakmilik" title="No Hakmilik"/>
        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Tuan Tanah" >
            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                <s:link beanclass="etanah.view.stripes.pengambilan.RekodBayaranPampasanTambahanActionBean"
                        event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                    <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                    ${senarai.pihak.nama}
                </s:link>
                    <br/>
            </c:forEach>
        </display:column>

    </display:table>
    </div>
</fieldset>
      <c:if test="${showDetails}">
          <table width="100%">
              <tr>
                  <td width="30%"><label >Jumlah bayaran pampasan tambahan yang diterima (RM) :</label></td>
                  <td><s:text name="jumTerimaPampasan" size="50" onkeyup="validateNumber(this,this.value);"/><br /></td>
              </tr>
              <tr>
                  <td><label >Jumlah pampasan yang diterima (RM) :</label></td>
                  <td><s:text name="jumTerimaPampasan" size="50" onkeyup="validateNumber(this,this.value);"/><br /></td>
              </tr>
              <tr>
                  <td><label >Cara Pembayaran : </label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran1" value="CT" /> Cek Tempatan <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran2" value="CL"/> Cek Luar <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran3" value="CB"/> Cek Bank Negara <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran4" value="DB"/> Bank Draf <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran5" value="T"/> Tunai <br /></td>
              </tr>
              <tr>
                  <td><label >&nbsp;&nbsp;</label></td>
                  <td><s:radio name="kodCaraBayaran.kod" id="kodCaraBayaran6" value="XT"/> Tiada Pembayaran</td>
              </tr>

              <tr>
                  <td><label >No. :</label></td>
                  <td><s:text name="noDok" size="50"/></td>
              </tr>
              <tr>
                  <td><label >Tarikh :</label></td>
                  <td><s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/></td>
              </tr>
              <tr>
                  <td><label >Bank :</label></td>
                  <td><s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                        </s:select>
                  </td>
              </tr>
            </table>
              <br/><br/>

              <div align="center">
                  <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                  <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                  <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              </div>

      <br />
       </c:if>

</div>
</s:form>


