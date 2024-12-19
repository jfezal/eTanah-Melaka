<%-- 
    Document   : RujukanPadaMahkamah
    Created on : June 03, 2011, 12:01:23 PM
    Author     : massita
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
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.RujukanPadaMahkamah_caterOneTTOnlyActionBean">
    <div id="hakmilik_details">
        <s:messages/>
        <s:errors/>
        <div align="center">
            <table style="width:90.2%" bgcolor="purple">
                <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                            <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">PERMOHONAN SUPAYA BANTAHAN DIRUJUKAN KEPADA MAHKAMAH
                            </font></font>
                        </div></td></tr>
            </table>
        </div><br /><br />
        <fieldset class="aras1">
            <c:if test="${actionBean.hakmilik ne null && actionBean.permohonanPihak ne null}" >
                <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
                <div align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>No Hakmilik</th><th>No Lot/No PT</th><th>Daerah</th><th>Bandar/Pekan/Mukim</th><th>Tuan Tanah</th>
                        </tr>
                        <tr>
                            <td>
                                ${actionBean.hakmilik.idHakmilik}
                            </td>
                            <td>
                                ${actionBean.hakmilik.lot.nama}${actionBean.hakmilik.noLot}
                            </td>
                            <td>
                                ${actionBean.hakmilik.daerah.nama}
                            </td>
                            <td>
                                ${actionBean.hakmilik.bandarPekanMukim.nama}
                            </td>
                            <td><c:forEach items="${actionBean.permohonanPihakPendepositList}" varStatus="loop" var="pendeposit">
                                    <s:link beanclass="etanah.view.stripes.pengambilan.RujukanPadaMahkamah_caterOneTTOnlyActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${pendeposit.pihak.idPihak}"/>
                                        <s:param name="idHakmilik" value="${actionBean.hakmilik.idHakmilik}"/>

                                        ${pendeposit.pihak.nama}

                                    </s:link><BR>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </fieldset>
        <c:if test="${showDetails}">
            <fieldset class="aras1">
                <%--<legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>--%>
                <div class="content" align="left">
                    <table align="left"  width="90%">
                        <tr>
                            <td align="left" class="input1" width="70%"><b>No Prosiding : </b><s:text name="prosiding"/></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"><b>Pemberitahuan Warta Kerajaan : </b><s:text name="warta"/></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"></td>
                        </tr>
                        <td align="left" width="30%"><b>1. Ringkasan bantahan:</b></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="ringkasanBantah"/></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td align="left" width="30%"><b>2. Berikut adalah kedudukan dan luasnya tanah itu,dan butir-butir mengenai apa-apa pokok, bangunan
                                    atau tanam-tanaman yang sedia keatasnya Kedudukan dan luasnya tanah itu, dan butir-butir mengenai apa-apa pokok, bangunan atau tanaman-tanaman yang sedia ada diatasnya :</b></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="butiranTanah"/></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td align="left" width="30%"><b>3. Berikut adalah nama dan alamat kesemua orang yang saya ada alasan untuk mempercayai sebagai mempunyai berkepentingan pada tanah itu :</b></td>
                        </tr>
                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <%--<div  align="center">
                            <display:table style="width:50%" class="tablecloth" name="${actionBean.permohonanPihak}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/rujukanPadaMahkamahCaterOneTT" id="line">
                            <display:column title="Nama" property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
                            <display:column title="Jenis Kepentingan" style="vertical-align:top;">${line.jenis.nama}</display:column>
                            </display:table>
                        </div>--%>
                        <tr>
                            <td align="left" width="70%"><i>Nama</i></td>
                        </tr>
                        <tr>
                            <td align="left" width="70%" style=" text-transform: capitalize"><b>${actionBean.permohonanPihak.pihak.nama}</b></td>
                        </tr>
                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <tr>
                            <td align="left" width="70%"><i>(Alamat)</i></td>
                        </tr>
                        <tr>
                            <td align="left" width="70%" style=" text-transform: capitalize">
                                <b>${actionBean.permohonanPihak.pihak.alamat1},&nbsp;
                                    ${actionBean.permohonanPihak.pihak.alamat2},&nbsp;
                                    ${actionBean.permohonanPihak.pihak.alamat3},&nbsp;
                                    ${actionBean.permohonanPihak.pihak.poskod},&nbsp;
                                    ${actionBean.permohonanPihak.pihak.negeri.nama}</b></td>
                        </tr>
                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <tr>
                            <td align="left" width="70%"><i>(Kepentingan)</i></td>
                        </tr>
                        <tr>
                            <td align="left" width="70%" style=" text-transform: capitalize">
                                <b>${actionBean.permohonanPihak.jenis.nama}</b></td>
                        </tr>

                        <tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>

                        <tr>
                            <td align="left" width="30%"><b>4. Pemberitahu-pemberitahu yang berikut telah disampaikan kepada pihak-pihak yang berkepentingan:</b></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="notis"/></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td align="left" width="30%"><b>5. Kenyataan-kenyataan secara bertulis yang berikut telah dibuat atau diserahkan oleh pihak-pihak yang berikut yang berkepentingan:</b></td>
                        </tr>
                        <tr>
                            <td align="left" class="input1" width="70%"><s:textarea rows="5" cols="150" name="pernyataan"/></td>
                        </tr>
                        <tr><td></td></tr>
                        <c:if test="${actionBean.kodUrusan eq 'SEK4'}">
                            <tr>
                                <td align="left" width="30%" rowspan="1"><b>6. Jumlah wang yang telah dihukum diberi kerana gantirugi di bawah Seksyen 6 Akta yang tersebut adalah sebanyak RM :
                                        <s:text name="amnTawarRosak" size="20" formatPattern="###,###,###.00" onkeyup="validateNumber(this,this.value);"/>
                                    </b></td>
                            </tr>
                            <tr><td></td></tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1"><b>7. Jumlah pampasan yang dihukum diberi dibawah Seksyen 14 ialah RM :
                                        <s:text name="amnTawarPampasan" disabled="true" size="20" formatPattern="###,###,###.00" onkeyup="validateNumber(this,this.value);"/>
                                    </b></td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.kodUrusan ne 'SEK4'}">
                            <tr>
                                <td align="left" width="30%" rowspan="1"><b>6. Jumlah wang yang telah dihukum diberi kerana gantirugi di bawah Seksyen 6 Akta yang tersebut adalah sebanyak RM :
                                        <s:text name="amnTawarRosak" size="20"  formatPattern="###,###,###.00" onkeyup="validateNumber(this,this.value);"/>
                                    </b></td>
                            </tr>
                            <tr><td></td></tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1"><b>7. Jumlah pampasan yang dihukum diberi dibawah Seksyen 14 ialah RM :
                                        <s:text name="amnTawarPampasan"  size="20" formatPattern="###,###,###.00" onkeyup="validateNumber(this,this.value);"/>
                                    </b></td>
                            </tr>
                        </c:if>

                        <tr><td></td></tr>
                        <tr>
                            <td align="left" width="30%" rowspan="1"><b>8. Jumlah pampasan itu telah diputuskan atas alasan-alasan yang berikut :</b></td>
                        </tr>
                        <tr>
                            <td align="left" width="70%"><s:textarea name="alasanAmnPampasan" rows="5" cols="150"/></td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td align="left" width="30%" rowspan="1"><b>9. Saya sertakan disini salinan-salinan suratan yang berikut :</b></td>
                        </tr>
                        <tr>
                            <td align="left" width="70%"><s:textarea name="lampiran" rows="5" cols="150"/></td>
                        </tr>
                        <tr><td></td></tr>
                    </table>
                </div>
            </fieldset>
            <br>
            <br/><br/>
            <div align="center">
                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </div>
            <br />
        </c:if>

    </div>
</s:form>



