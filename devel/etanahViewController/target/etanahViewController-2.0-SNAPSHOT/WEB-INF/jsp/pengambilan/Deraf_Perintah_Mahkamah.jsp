<%--
    Document   : Deraf_Perintah_Mahkamah
    Created on : Mar 29, 2010, 12:01:23 PM
    Author     : Hazirah
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
           });

    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

    function validationTanah() {
        var tanahItem = $("#tanahItem").val();
        var tanahAmaun = $("#tanahAmaun").val();

        if(tanahItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#tanahItem").focus();
            return false;
        }

        if(tanahAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#tanahAmaun").focus();
            return false;
        }
        return true;
    }

    function validationBngn() {
        var bngnItem = $("#bngnItem").val();
        var bngnAmaun = $("#bngnAmaun").val();

        if(bngnItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#bngnItem").focus();
            return false;
        }

        if(bngnAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#bngnAmaun").focus();
            return false;
        }
        return true;
    }

    function validationLain() {
        var lainItem = $("#lainItem").val();
        var lainAmaun = $("#lainAmaun").val();

        if(lainItem == ""){
            alert('Sila pilih " Item : " terlebih dahulu.');
            $("#lainItem").focus();
            return false;
        }

        if(lainAmaun == ""){
            alert('Sila pilih " RM : " terlebih dahulu.');
            $("#lainAmaun").focus();
            return false;
        }
        return true;
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
    function removeNilai(idPenilaian)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/perintahMahkamah?deleteNilai&idPenilaian='+idPenilaian;
            $.get(url,
            function(data){
                $('#page_div').html(data);
    <%--self.opener.refreshPageTanahRizab();--%>
                },'html');
            }
        }
</script>
<s:form beanclass="etanah.view.stripes.pengambilan.PerintahMahkamahActionBean">
    <c:if test="${showView}">
        <div  id="hakmilik_details">
            <s:messages/>
            <s:errors/>

            <fieldset class="aras1"><br/>
                <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatPampasan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahMahkamahActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="true"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </div>
            </fieldset>
            <br /><br />

            <c:if test="${actionBean.hakmilik ne null}">
                <s:errors/>
                <c:if test="${showTuanTanah}">
                    <fieldset class="aras1">
                        <legend>Tuan Tanah</legend><br />
                        <div align="center">Sila klik nama tuan tanah.</div>
                        <div align="center">

                            <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik" />
                                <display:column property="noLot" title="Nombor Lot/PT" />
                                <display:column title="Daerah" property="daerah.nama" class="daerah" />
                                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.aktif eq 'Y'}">
                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahMahkamahActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                                <s:param name="display" value="true"/>
                                            </s:link>
                                            <br/>
                                            No KP ${senarai.pihak.noPengenalan}
                                            <%--</c:if>--%>

                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">

                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}

                                    <br/>
                                    No KP ${senarai.pihak.noPengenalan}

                                </c:if>--%>

                                            <br/>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                            </display:table>
                        </div>
                        <br /><br />
                    </fieldset><br />
                </c:if>
            </c:if>

            <fieldset class="aras1">
                <c:if test="${showDetails}">
                    <s:hidden name="idPihak" />
                    <s:hidden name="idHakmilik"/>
                    <s:hidden name="display"/>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr>
                                <td align="center"><font color="#000000"><b>DALAM MAHKAMAH TINGGI MALAYA DI SEREMBAN</b></font></td>
                            </tr>
                            <tr>
                                <td align="center"><font color="#000000"><b>SAMAN PEMULA BIL : </b></font><s:text name="samanPemulaBil" value="${actionBean.permohonanMahkamah.samanPemulaBil}" disabled="true" size="10" style="text-align:left" id="samanPemulaBil" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td align="center"><font color="#000000"><b>___________________________________________________________________________________________________________________________________</b></font></td>
                            </tr>

                            <table align="right" width="30%">
                                <tr>
                                    <td><font color="#000000">Dalam perkara seksyen 29 Akta Pengambilan Tanah, 1960</font></td>
                                </tr>
                                <tr>
                                    <td align="center"><font color="#000000">DAN</font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000">Dalam perkara mengenai tanah yang terkandung dalam ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama} Daerah ${actionBean.hakmilik.daerah.nama}.</font></td>
                                </tr>
                            </table>
                            <br /><br><br><br><br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000">Pentadbir Tanah Daerah</font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000">Seremban</font></td>
                                </tr>
                            </table>
                            <table align="right" width="30%">
                                <tr>
                                    <td><font color="#000000">Pemohon</font></td>
                                </tr>
                            </table>
                            <br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000"><b>DIHADAPAN PENOLONG KANAN PENDAFTAR</b></font></td>
                                </tr>
                                <tr>
                                    <td><s:text name="namaPenolongKananPendaftar" size="43" id="namaPenolongKananPendaftar" /></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>MAHKAMAH TINGGI</b></font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>SEREMBAN</b></font></td>
                                </tr>
                            </table>
                            <br><br><br><br><br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000">PADA</font></td>
                                    <td>:</td>
                                    <td><s:text disabled="true" id="tarikhTerimaPerintah" name="tarikhTerimaPerintah"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhTerimaPerintah}"/></s:text></td>
                                    </tr>
                                </table>
                                <br>
                                <table align="right" width="30%">
                                    <tr>
                                        <td><font color="#000000"><u>DALAM KAMAR</u></font></td>
                                    </tr>
                                </table>
                                <br>
                                <br>

                                <tr>
                                    <td><font color="#000000"><u><b>PERINTAH</b></u></font></td>
                                </tr>
                                <br><br>
                                <table align="center" width="50%">
                                    <tr>
                                        <td><font color="#000000"><u>ATAS PERMOHONAN</u></font><font color="#000000"> <s:text disabled="true" name="alasanBantah" size="43" value="${actionBean.permohonanMahkamah.alasanBantah}" id="alasanBantah" onkeyup="this.value=this.value.toUpperCase();"/> Daerah ${actionBean.hakmilik.daerah.nama} dan </font>
                                <font color="#000000"><u>SETELAH MEMBACA</u></font><font color="#000000"> Affidavit ${actionBean.permohonanKertas.wakilSidang} yang diikrarkan pada ${actionBean.permohonanMahkamah.tarikhIkrar}, </font></td></tr>
                                <tr><td><font color="#000000"><u>MAKA, ADALAH DIPERINTAHKAN</u></font><font color="#000000"> bahawa pemohon dengan ini dibenarkan
                                <font color="#000000"><b>mendepositkan</b></font></font></td></tr>
                                <c:set value="${actionBean.permohonanPihak.syerPembilang}" var="a"/>
                                <c:set value="${actionBean.permohonanPihak.syerPenyebut}" var="b"/>
                                <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                <%--<c:set value="${a/b*c}" var="e"/>
                                <c:set value="${e*d}" var="f"/>--%>
                                <c:set value="${d*(a/b)}" var="e"/>
                                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                    <c:if test="${actionBean.permohonanPihak.pihak.idPihak == list.pihak.pihak.idPihak}">
                                        <c:set var="B" value="0"/>
                                        <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:forEach>
                                        <c:set value="${B}" var="g"/>
                                    </c:if>
                                </c:forEach>
                                <tr><td>di Mahkamah ini sejumlah wang sebanyak RM <fmt:formatNumber pattern="#,##0.00" value="${e}"/> </td></tr>
                                <%--<fmt:formatNumber value="${actionBean.am}" pattern="#,##0.00"/>--%>

                                <%--<c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                    <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                        <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPembilang}" var="a"/>
                                        <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPenyebut}" var="b"/>
                                        <c:set value="${(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                        <c:set value="${d*(a/b)}" var="e"/>
                                        <fmt:formatNumber pattern="#,##0.00" value="${e}"/>
                                    </c:if>
                                </c:forEach>--%>
                                <tr><td>iaitu bayaran pampasan pengambilan tanah yang ditawarkan kepada tuan punya tanah ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} hakmilik
                                        ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}. </td>
                                </tr>
                            </table>
                            <br><br><br><br><br>
                            <table align="left">
                                <tr>
                                    <td><font color="#000000">Bertarikh pada</font></td>
                                    <td>:</td>
                                    <td><s:text disabled="true" id="tarikhTerimaPerintah" name="tarikhTerimaPerintah"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanMahkamah.tarikhTerimaPerintah}"/></s:text></td>
                                    </tr>
                                </table>
                                <br><br><br><br><br>
                                <table align="right" width="30%">
                                    <tr>
                                        <td><font color="#000000"><b>Timbalan Pendaftar</b></font></td>
                                    </tr>
                                    <tr>
                                        <td><font color="#000000"><b>Mahkamah Tinggi</b></font></td>
                                    </tr>
                                    <tr>
                                        <td><font color="#000000"><b>Seremban</b></font></td>
                                    </tr>
                                </table>
                                <br><br><br><br>
                                <table align="left">
                                    <tr>
                                        <td><font color="#000000">Perintah ini difailkan oleh Pentadbir Tanah Daerah Seremban</font></td>
                                    </tr>
                                </table>
                            </table>
                            <br><br><br>
                            <p align="center">

                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </div>
                </fieldset>

                <br/><br/>
            </c:if>

        </div>
    </c:if>

    <c:if test="${showEdit}">
        <div  id="hakmilik_details">
            <s:messages/>
            <s:errors/>

            <fieldset class="aras1"><br/>
                <legend align="left"><b>Maklumat Hakmilik Permohonan</b></legend>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumatPampasan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahMahkamahActionBean"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="false"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}</s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </div>
            </fieldset>
            <br /><br />

            <c:if test="${actionBean.hakmilik ne null}">
                <s:errors/>
                <c:if test="${showTuanTanah}">
                    <fieldset class="aras1">
                        <legend>Tuan Tanah</legend><br />
                        <div align="center">*Sila klik nama tuan tanah</div>
                        <div align="center">

                            <display:table class="tablecloth" name="${actionBean.hakmilik}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="No" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column property="idHakmilik" title="ID Hakmilik" />
                                <display:column property="noLot" title="Nombor Lot/PT" />
                                <display:column title="Daerah" property="daerah.nama" class="daerah" />
                                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                                <display:column title="Orang Berkepentingan" style="vertical-align:baseline">
                                    <c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                        <c:if test="${senarai.aktif eq 'Y'}">
                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '0'}">--%>
                                            <s:link beanclass="etanah.view.stripes.pengambilan.PerintahMahkamahActionBean"
                                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}
                                                <s:param name="display" value="false"/>
                                            </s:link>
                                            <br/>
                                            No KP ${senarai.pihak.noPengenalan}
                                            <%--</c:if>--%>

                                            <%--<c:if test="${actionBean.perbicaraanKehadiran.bantahElektrik eq '1'}">

                                    <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>${senarai.pihak.nama}

                                    <br/>
                                    No KP ${senarai.pihak.noPengenalan}

                                </c:if>--%>

                                            <br/>
                                        </c:if>
                                    </c:forEach>
                                </display:column>
                                <display:column title="Luas Diambil" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/>&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}</display:column>
                            </display:table>
                        </div>
                        <br /><br />
                    </fieldset><br />
                </c:if>
            </c:if>

            <fieldset class="aras1">
                <c:if test="${showDetails}">
                    <div class="content" align="center">
                        <s:hidden name="idPihak" />
                        <s:hidden name="idHakmilik"/>
                        <s:hidden name="display"/>
                        <table border="0" width="80%">
                            <tr>
                                <td align="center"><font color="#000000"><b>DALAM MAHKAMAH TINGGI MALAYA DI SEREMBAN</b></font></td>
                            </tr>
                            <tr>
                                <td align="center"><font color="#000000"><b>SAMAN PEMULA BIL : </b></font><s:text name="samanPemulaBil" disabled="true" value="${actionBean.permohonanMahkamah.samanPemulaBil}"  size="10" style="text-align:left" id="samanPemulaBil" class="normal_text"/></td>
                            </tr>
                            <tr>
                                <td align="center"><font color="#000000"><b>___________________________________________________________________________________________________________________________________</b></font></td>
                            </tr>

                            <table align="right" width="30%">
                                <tr>
                                    <td><font color="#000000">Dalam perkara seksyen 29 Akta Pengambilan Tanah, 1960</font></td>
                                </tr>
                                <tr>
                                    <td align="center"><font color="#000000">DAN</font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000">Dalam perkara mengenai tanah yang terkandung dalam ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama} Daerah ${actionBean.hakmilik.daerah.nama}.</font></td>
                                </tr>
                            </table>
                            <br /><br><br><br><br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000">Pentadbir Tanah Daerah</font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000">Seremban</font></td>
                                </tr>
                            </table>
                            <table align="right" width="30%">
                                <tr>
                                    <td><font color="#000000">Pemohon</font></td>
                                </tr>
                            </table>
                            <br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000"><b>DIHADAPAN PENOLONG KANAN PENDAFTAR</b></font></td>
                                </tr>
                                <tr>
                                    <td><s:text name="namaPenolongKananPendaftar" value="${actionBean.permohonanMahkamah.namaPenolongKananPendaftar}" size="43" id="namaPenolongKananPendaftar" onkeyup="this.value=this.value.toUpperCase();"/></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>MAHKAMAH TINGGI</b></font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>SEREMBAN</b></font></td>
                                </tr>
                            </table>
                            <br><br><br><br><br><br><br>
                            <table align="left" width="40%">
                                <tr>
                                    <td><font color="#000000">PADA</font></td>
                                    <td>:</td>
                                    <td><s:text class="datepicker" id="tarikhTerimaPerintah" name="tarikhTerimaPerintah" formatPattern="dd/MM/yyyy" style="width:110px;" value="${actionBean.permohonanMahkamah.tarikhTerimaPerintah}"></s:text></td>
                                    </tr>
                                </table>
                                <br>
                                <table align="right" width="30%">
                                    <tr>
                                        <td><font color="#000000"><u>DALAM KAMAR</u></font></td>
                                    </tr>
                                </table>
                                <br>
                                <br>

                                <tr>
                                    <td><font color="#000000"><u><b>PERINTAH</b></u></font></td>
                                </tr>
                                <br><br>
                                <table align="center" width="50%">
                                    <tr>
                                        <td><font color="#000000"><u>ATAS PERMOHONAN</u></font><font color="#000000"> <s:text name="alasanBantah" size="43" value="${actionBean.permohonanMahkamah.alasanBantah}" id="alasanBantah" onkeyup="this.value=this.value.toUpperCase();"/> Daerah ${actionBean.hakmilik.daerah.nama} dan </font>
                                <font color="#000000"><u>SETELAH MEMBACA</u></font><font color="#000000"> Affidavit <s:text name="ringkasanBantah" size="43" value="${actionBean.permohonanMahkamah.ringkasanBantah}" id="ringkasanBantah" onkeyup="this.value=this.value.toUpperCase();"/> yang diikrarkan pada ${actionBean.permohonanMahkamah.tarikhIkrar}, </font></td></tr>
                                <tr><td><font color="#000000"><u>MAKA, ADALAH DIPERINTAHKAN</u></font><font color="#000000"> bahawa pemohon dengan ini dibenarkan
                                <font color="#000000"><b>mendepositkan</b></font></font></td></tr>
                                <c:set value="${actionBean.permohonanPihak.syerPembilang}" var="a"/>
                                <c:set value="${actionBean.permohonanPihak.syerPenyebut}" var="b"/>
                                <c:set value="${actionBean.hakmilikPermohonan.luasTerlibat}" var="c"/>
                                <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                <%--<c:set value="${a/b*c}" var="e"/>
                                <c:set value="${e*d}" var="f"/>--%>
                                <c:set value="${d*(a/b)}" var="e"/>
                                <c:forEach items="${actionBean.senaraiPerbicaraanKehadiran}" var="list">
                                    <c:if test="${actionBean.permohonanPihak.pihak.idPihak == list.pihak.pihak.idPihak}">
                                        <c:set var="B" value="0"/>
                                        <c:forEach items="${list.senaraiPenilaian}" var="list1">
                                            <c:set value="${B + list1.amaun}" var="B"/>
                                        </c:forEach>
                                        <c:set value="${B}" var="g"/>
                                    </c:if>
                                </c:forEach>
                                <tr><td>di Mahkamah ini sejumlah wang sebanyak RM <fmt:formatNumber pattern="#,##0.00" value="${e}"/> </td></tr>
                                <%--<fmt:formatNumber value="${actionBean.am}" pattern="#,##0.00"/>--%>

                                <%--<c:forEach items="${actionBean.hakmilik.senaraiPihakBerkepentingan}" var="list">
                                    <c:if test="${line.pihak.pihak.idPihak == list.pihak.idPihak}">
                                        <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPembilang}" var="a"/>
                                        <c:set value="${actionBean.senaraiPerbicaraanKehadiran.pihak.syerPenyebut}" var="b"/>
                                        <c:set value="${(actionBean.hakmilikPermohonan.luasTerlibat)}" var="c"/>
                                        <c:set value="${(actionBean.hakmilikPerbicaraan.keputusanNilai)*(actionBean.hakmilikPermohonan.luasTerlibat)}" var="d"/>
                                        <c:set value="${d*(a/b)}" var="e"/>
                                        <fmt:formatNumber pattern="#,##0.00" value="${e}"/>
                                    </c:if>
                                </c:forEach>--%>
                                <tr><td>iaitu bayaran pampasan pengambilan tanah yang ditawarkan kepada tuan punya tanah ${actionBean.hakmilik.lot.nama} ${actionBean.hakmilik.noLot} hakmilik
                                        ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} di ${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah ${actionBean.hakmilik.daerah.nama}. </td>
                                </tr>
                            </table>
                            <br><br><br><br><br>
                            <table align="left">
                                <tr>
                                    <td><font color="#000000">Bertarikh pada</font></td>
                                    <td>:</td>
                                    <td><s:text class="datepicker"  id="tarikhTerimaPerintah2" name="tarikhTerimaPerintah" formatPattern="dd/MM/yyyy" style="width:110px;" value="${actionBean.permohonanMahkamah.infoAudit.tarikhMasuk}" />
                                </tr>
                            </table>
                            <br><br><br><br><br>
                            <table align="right" width="30%">
                                <tr>
                                    <td><font color="#000000"><b>Timbalan Pendaftar</b></font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>Mahkamah Tinggi</b></font></td>
                                </tr>
                                <tr>
                                    <td><font color="#000000"><b>Seremban</b></font></td>
                                </tr>
                            </table>
                            <br><br><br><br>
                            <table align="left">
                                <tr>
                                    <td><font color="#000000">Perintah ini difailkan oleh Pentadbir Tanah Daerah Seremban</font></td>
                                </tr>
                            </table>
                        </table>
                        <br><br><br>
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </div>
                </fieldset>

                <br/><br/>
            </c:if>

        </div>
    </c:if>
</s:form>



