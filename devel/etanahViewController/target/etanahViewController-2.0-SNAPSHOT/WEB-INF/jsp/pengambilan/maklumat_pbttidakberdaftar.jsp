<%--
    Document   : maklumat_pbttidakberdaftar
    Created on : 15-Dec-2011, 10:38:48
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
    function ajaxLink(link, update) {
    <%--alert("masuk idhakmilik");--%>
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }

        function tambahKehadiran(tableID) {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var cell1 = row.insertCell(0);
            cell1.innerHTML = rowCount;
    <%--var element1 = document.createElement("input");
    element1.type = "checkbox";
    cell1.appendChild(element1);--%>

            var cell2 = row.insertCell(1);
            var element1 = document.createElement("input");
            element1.type = "text";
            cell2.appendChild(element1);

            var cell3 = row.insertCell(2);
            var element2 = document.createElement("input");
            element2.type = "text";
            cell3.appendChild(element2);
        }

        function popup(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?showEditTuanTanah&idPermohonanPhkTdkBerptg='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }


        function tambah(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?showTuanTanahPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function  test(){
            var tujuan1=document.getElementById("tujuan1");
            var trkDate=document.getElementById("trkDate");
            var trkYear=document.getElementById("trkYear");
            if(tujuan1.checked){
                trkYear.value="";
                trkDate.disabled=false;
                trkYear.disabled=true;
            }else{
                trkDate.value="";
                trkDate.disabled=true;
                trkYear.disabled=false;
            }
        }

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

        function removeNilai(idPermohonanPhkTdkBerptg)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?deletePbt&idPermohonanPhkTdkBerptg='
                    +idPermohonanPhkTdkBerptg;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }

        function refreshPagePBT(){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumatpbttidakberdaftar?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

</script>

<s:form name="notaForm" beanclass="etanah.view.stripes.pengambilan.maklumatpbttidakberdaftarActionBean">
    <s:messages/>
    <c:if test="${edit}">
        <div  id="hakmilik_details">
            <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
            <fieldset class="aras1">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatpbttidakberdaftar" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.maklumatpbttidakberdaftarActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                                <s:param name="display" value="false"/>
                            </s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Syarat Nyata" />
                    </display:table>
                </p>
                <br/><br/><br/>

                <c:if test="${actionBean.hakmilik ne null}">
                    Maklumat Pihak Berkepentingan Tidak Berdaftar 
                    <br/><br/>

                    <p align="center">
                        <br>
                        <display:table class="tablecloth" name="${actionBean.senaraiPPTB}"  cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/maklumatpbttidakberdaftar" id="line1">
                            <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                            <display:column title="Nama" >
                                <c:if test="${line1.nama ne null}"> ${line1.nama} </c:if>
                                <c:if test="${line1.nama eq null}"> - </c:if>
                            </display:column>
                            <display:column title="No KP" >
                                <c:if test="${line1.nomborPengenalan ne null}"> ${line1.nomborPengenalan} </c:if>
                                <c:if test="${line1.nomborPengenalan eq null}"> - </c:if>
                            </display:column>
                            <display:column title="Alamat" style="vertical-align:baseline">
                                ${line1.alamat1}
                                <c:if test="${line1.alamat2 ne null}"> , ${line1.alamat2} </c:if>
                                <c:if test="${line1.alamat3 ne null}"> , ${line1.alamat3} </c:if>
                                <c:if test="${line1.alamat4 ne null}"> , ${line1.alamat4} </c:if>
                            </display:column>
                            <display:column title="Poskod">
                                <c:if test="${line1.poskod ne null}">
                                    ${line1.poskod}
                                </c:if>
                                <c:if test="${line1.poskod eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Negeri">
                                <c:if test="${line1.kodNegeri ne null}">
                                    ${line1.kodNegeri.nama}
                                </c:if>
                                <c:if test="${line1.kodNegeri eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="No Tel" style="vertical-align:baseline">
                                <c:if test="${line1.nomborTelefon1 ne null}">
                                    ${line1.nomborTelefon1}
                                    <c:if test="${line1.nomborTelefon2 ne null}">, ${line1.nomborTelefon2} </c:if>
                                </c:if>
                                <c:if test="${line1.nomborTelefon1 eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Bank" style="vertical-align:baseline">
                                <c:if test="${line1.kodBank.kod ne null}">
                                    ${line1.kodBank.nama}
                                </c:if>
                                <c:if test="${line1.kodBank.kod eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="No Akaun" style="vertical-align:baseline">
                                <c:if test="${line1.nomborAkaunBank ne null}">
                                    ${line1.nomborAkaunBank}
                                </c:if>
                                <c:if test="${line1.nomborAkaunBank eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' onclick="popup('${line1.idPermohonanPhkTdkBerptg}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${line1.idPermohonanPhkTdkBerptg}');" />
                            </div>
                        </display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah();"/>&nbsp;
                    <%--<s:button class="btn" value="Hapus" name="remove" id="remove" onclick="deleteKehadiran('line1');"/>&nbsp;--%>
                    <%--</p>--%>
                    <br/><br/>

                </c:if>
            </fieldset>
        </div>

    </c:if>

    <c:if test="${!edit}">
        <div  id="hakmilik_details">
            <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
            <fieldset class="aras1">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatpbttidakberdaftar" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.maklumatpbttidakberdaftarActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                                <s:param name="display" value="true"/>
                            </s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Luas Sebenar"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Syarat Nyata" />
                    </display:table>
                </p>
                <br/><br/><br/>

                <c:if test="${actionBean.hakmilik ne null}">
                    <%--  <c:if test="${actionBean.display eq 'true'}">
                      <c:if test="${edit}">--%>


                    Maklumat Pihak Berkepentingan Tidak Berdaftar
                    <br/><br/>

                    <p align="center">
                        <br>
                        <display:table class="tablecloth" name="${actionBean.senaraiPPTB}"  cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/maklumatpbttidakberdaftar" id="line1">
                            <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                            <display:column title="Nama" >
                                <c:if test="${line1.nama ne null}"> ${line1.nama} </c:if>
                                <c:if test="${line1.nama eq null}"> - </c:if>
                            </display:column>
                            <display:column title="No KP" >
                                <c:if test="${line1.nomborPengenalan ne null}"> ${line1.nomborPengenalan} </c:if>
                                <c:if test="${line1.nomborPengenalan eq null}"> - </c:if>
                            </display:column>
                            <display:column title="Alamat" style="vertical-align:baseline">
                                ${line1.alamat1}
                                <c:if test="${line1.alamat2 ne null}"> , ${line1.alamat2} </c:if>
                                <c:if test="${line1.alamat3 ne null}"> , ${line1.alamat3} </c:if>
                                <c:if test="${line1.alamat4 ne null}"> , ${line1.alamat4} </c:if>
                            </display:column>
                            <display:column title="Poskod">
                                <c:if test="${line1.poskod ne null}">
                                    ${line1.poskod}
                                </c:if>
                                <c:if test="${line1.poskod eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Negeri">
                                <c:if test="${line1.kodNegeri ne null}">
                                    ${line1.kodNegeri.nama}
                                </c:if>
                                <c:if test="${line1.kodNegeri eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="No Tel" style="vertical-align:baseline">
                                <c:if test="${line1.nomborTelefon1 ne null}">
                                    ${line1.nomborTelefon1}
                                    <c:if test="${line1.nomborTelefon2 ne null}">, ${line1.nomborTelefon2} </c:if>
                                </c:if>
                                <c:if test="${line1.nomborTelefon1 eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Bank" style="vertical-align:baseline">
                                <c:if test="${line1.kodBank.kod ne null}">
                                    ${line1.kodBank.nama}
                                </c:if>
                                <c:if test="${line1.kodBank.kod eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="No Akaun" style="vertical-align:baseline">
                                <c:if test="${line1.nomborAkaunBank ne null}">
                                    ${line1.nomborAkaunBank}
                                </c:if>
                                <c:if test="${line1.nomborAkaunBank eq null}">
                                    -
                                </c:if>
                            </display:column>
                            <display:column title="Kemaskini">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' onclick="popup('${line1.idPermohonanPhkTdkBerptg}');return false;" onmouseover="this.style.cursor='pointer';">
                            </p>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNilai('${line1.idPermohonanPhkTdkBerptg}');" />
                            </div>
                        </display:column>
                    </display:table>
                    <br/><br/>
                </c:if>

            </fieldset>
        </div>
    </c:if>

</s:form>
