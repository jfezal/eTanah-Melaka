<%--
    Document   : maklumat_jadualpetak
    Created on : Dec 16, 2011, 10:42:01 AM
    Author     : murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {

        $('#menara_bgnn').hide();
    });
    function changebgnn(value){

        if(value == "Y")
        {
            $('#menara_bgnn').show();

        }
        if(value == "N")
        {
            $('#menara_bgnn').hide();
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

    function popup(b,p,idT){

        window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm2&namaBangunan="+b+"&tingkat="+p+"&idTingkat="+idT, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=250");
    }

    function popupPetakAksesori(b,t,p){

        window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm3&idBangunan="+b+"&idTingkat="+t+"&idPetak="+p, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=200");
    }

    function removePtkAksr(val){

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?deletePtkAksr&idAksesori='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');
    }   

    function selectedBangunan(idBangunanVal){
        document.getElementById("tempIdBangunan").value = idBangunanVal;
        var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?selectedBangunan&idBangunan='+idBangunanVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function selectedTingkat(idBangunanVal,idTingkatVal){
        document.getElementById("tempIdTingkat").value = idTingkatVal;
        var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?selectedTingkat&idBangunan='+idBangunanVal+'&idTingkat='+idTingkatVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function selectedPetak(idBangunanVal,idTingkatVal,idPetakVal){
        document.getElementById("tempIdPetak").value = idPetakVal;
        var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?selectedPitak&idBangunan='+idBangunanVal+'&idTingkat='+idTingkatVal+"&idPetak="+idPetakVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>
<table><tr><td>
            <s:form beanclass="etanah.view.strata.MaintainBangunanActionBean">
                <s:messages/>
                <s:errors/>
                <c:if test="${manual}">
                    <p><font color="red">Arahan : Sila hapus Jadual Petak Manual terlebih dahulu.</font></p>
                </c:if>
                <c:if test="${!manual}">
                    <c:if test="${actionBean.kodurusan eq 'PBBS' || actionBean.kodurusan eq 'RMHS1'
                                  || actionBean.kodurusan eq 'PHPC' || actionBean.kodurusan eq 'RMHS6'
                                  || actionBean.kodurusan eq 'PHPP' || actionBean.kodurusan eq 'RMHS7'
                                  || actionBean.kodurusan eq 'RMH1A' || actionBean.kodurusan eq 'RMHS5'}">
                        <c:if test="${actionBean.stageId eq 'kemasukan'}">
                            <c:if test="${!ekstrak}">
                                <p><font color="red">Sila klik tab 'Dokumen' untuk memuatnaik fail xml Jadual Petak (JPP).</font></p>
                            </c:if>
                            <br>
                            <c:if test="${ekstrak && !(fn:length(actionBean.pBangunanL) > 0)}">
                                <div class="subtitle">
                                    <fieldset class="aras1">
                                        <s:button name="extrakBangunan" value="Ekstrak XML" class="longbtn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/> &nbsp;&nbsp;&nbsp;
                                        <br /><p><font color="red">*</font>Sila klik 'Ekstrak XML' untuk papar Jadual Petak-petak.</p>
                                    </fieldset>
                                </div>
                            </c:if>
                            <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
                                <div class="subtitle">
                                    <fieldset class="aras1">
                                        <s:button name="HapusXml" value="Hapus XML" class="longbtn" onclick="if(confirm('Adakah anda pasti untuk menghapus Jadual Petak-petak?'))doSubmit(this.form, this.name,'page_div');"/>
                                        <br><p><font color="red">*</font>Sila klik 'Hapus XML' untuk hapus Jadual Petak-petak.</p>
                                    </fieldset>
                                </div>
                            </c:if>
                        </c:if>
                    </c:if>
                    <br />
                    <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
                        <br>
                        <center><p><font color="blue"><b>Jumlah Unit Syer: <s:text name="mohonSkim.jumlahUnitSyer" readonly="true" size="8"/>
                                    </b></font></p></center>
                        <br />
                        <div class="subtitle">
                            <fieldset class="aras1">
                                <legend>Senarai Jadual Petak-petak</legend>
                                <p>
                                <table class="tablecloth" width="100%">
                                    <tr>
                                        <th>Bangunan</th>
                                        <th></th>
                                        <th>Senarai Tingkat</th>
                                        <th></th>
                                        <th>Senarai Petak</th>
                                        <%--<th>Keluasan <br><font size="1">(M)</font>2</th>--%>
                                        <th>Unit-unit Syer</th>
                                        <th>Jenis Kegunaan</th>
                                        <th></th>
                                        <th></th>
                                        <th>Petak Aksesori</th>
                                        <th>Jenis Kegunaan</th>
                                        <th width="16%">Lokasi</th>
                                    </tr>
                                    <c:set var="items" value="0"/>
                                    <c:set var="items2" value="0"/>
                                    <c:set var="items3" value="0"/>

                                    <s:hidden name="tempIdBangunan" id="tempIdBangunan" value="" />
                                    <s:hidden name="tempIdTingkat" id="tempIdTingkat" value="" />
                                    <s:hidden name="tempIdPetak" id="tempIdPetak" value="" />

                                    <c:forEach items="${actionBean.pBangunanL}" var="bgn" varStatus="statusB">
                                        <tr>
                                            <td>${bgn.nama}
                                            </td>
                                            <td>
                                                <c:if test="${fn:length(bgn.senaraiTingkat) > 0}" >
                                                    <c:if test="${actionBean.tempIdBangunan ne bgn.idBangunan}">
                                                        <div align="center">
                                                            <img  title="Klik Untuk Papar Tingkat" alt='Klik Untuk Papar Tingkat' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                  onclick="selectedBangunan('${bgn.idBangunan}');" onmouseover="this.style.cursor='pointer';" >
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${actionBean.tempIdBangunan eq bgn.idBangunan}">
                                                        <div align="center">
                                                            <img  title="Klik Untuk Papar Tingkat" alt='Klik Untuk Papar Tingkat' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                  onclick="selectedBangunan('');" onmouseover="this.style.cursor='pointer';" >
                                                        </div>
                                                    </c:if>

                                                </c:if>
                                            </td>

                                            <c:if test="${actionBean.tempIdBangunan eq bgn.idBangunan}">   <%--added for Bangunan--%>

                                                <c:forEach items="${bgn.senaraiTingkat}" var="tgkt" varStatus="status">

                                                    <td>${tgkt.tingkat}</td>

                                                    <td>
                                                        <%--Tingkat--%>
                                                        <c:if test="${fn:length(tgkt.senaraiPetak) > 0}" >
                                                            <c:if test="${actionBean.tempIdTingkat ne tgkt.idTingkat}">
                                                                <div align="center">
                                                                    <img  title="Klik Untuk Papar Petak" alt='Klik Untuk Papar Petak' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                          onclick="selectedTingkat('${bgn.idBangunan}','${tgkt.idTingkat}');" onmouseover="this.style.cursor='pointer';" >
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${actionBean.tempIdTingkat eq tgkt.idTingkat}">
                                                                <div align="center">
                                                                    <img  title="Klik Untuk Papar Petak" alt='Klik Untuk Papar Petak' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                          onclick="selectedBangunan('${bgn.idBangunan}');" onmouseover="this.style.cursor='pointer';" >
                                                                </div>
                                                            </c:if>

                                                        </c:if>
                                                    </td>

                                                    <c:if test="${fn:length(tgkt.senaraiPetak) > 0}">

                                                        <c:if test="${actionBean.tempIdTingkat eq tgkt.idTingkat}">   <%--Tingkat--%>

                                                            <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                                                <td>${petak.nama}</td>
                                                                <%--<td><s:text name="petakluas"  size="10" readonly="readonly" value="${petak.luas}"/></td>--%>
                                                                <td><s:text name="petaksyer" size="12" readonly="readonly" value="${petak.syer}"/></td>
                                                                <td><c:set var="rowNo" value="${items}" />
                                                                    <c:set var="petakKegunaanVal" value="" />
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '01'}">
                                                                        <c:set var="petakKegunaanVal" value="Pejabat" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '02'}">
                                                                        <c:set var="petakKegunaanVal" value="Kediaman" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '03'}">
                                                                        <c:set var="petakKegunaanVal" value="Perniagaan" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '04'}">
                                                                        <c:set var="petakKegunaanVal" value="Surau" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '05'}">
                                                                        <c:set var="petakKegunaanVal" value="Dewan" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq '06'}">
                                                                        <c:set var="petakKegunaanVal" value="Bilik TNB" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq 'REU1'}">
                                                                        <c:set var="petakKegunaanVal" value="Kondominium" />
                                                                    </c:if>
                                                                    <c:if test="${actionBean.petakKegunaan[rowNo] eq 'CBU11'}">
                                                                        <c:set var="petakKegunaanVal" value="Kondominium" />
                                                                    </c:if>
                                                                    <s:text name="petakKegunaan[${items}]"  readonly="readonly" />
                                                                <td></td>

                                                                <td>
                                                                    <c:if test="${fn:length(petak.senaraiPetakAksesori) > 0}" >
                                                                        <c:if test="${actionBean.tempIdPetak ne petak.idPetak}">
                                                                            <div align="center">
                                                                                <img  title="Klik Untuk Papar Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                                      onclick="selectedPetak('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">
                                                                            <div align="center">
                                                                                <img  title="Klik Untuk Papar Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                                      onclick="selectedTingkat('${bgn.idBangunan}','${tgkt.idTingkat}');" onmouseover="this.style.cursor='pointer';" >
                                                                            </div>
                                                                        </c:if>

                                                                    </c:if>
                                                                </td>

                                                                <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">

                                                                    <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                                                        <td>${petakAksesori.nama}</td>
                                                                        <td>
                                                                            <c:set var="rowNo" value="${items}" />
                                                                            <c:set var="petakKegunaanAksrVal" value="" />
                                                                            <c:if test="${actionBean.petakKegunaanAksr[rowNo] eq '99'}">
                                                                                <c:set var="petakKegunaanAksrVal" value="Lain-Lain" />
                                                                            </c:if>
                                                                            <c:if test="${actionBean.petakKegunaanAksr[rowNo] eq '04'}">
                                                                                <c:set var="petakKegunaanAksrVal" value="Ramp" />
                                                                            </c:if>
                                                                            <c:if test="${actionBean.petakKegunaanAksr[rowNo] eq '02'}">
                                                                                <c:set var="petakKegunaanAksrVal" value="Tempat Letak Bunga" />
                                                                            </c:if>
                                                                            <c:if test="${actionBean.petakKegunaanAksr[rowNo] eq '01'}">
                                                                                <c:set var="petakKegunaanAksrVal" value="Tempat Letak Kenderaan" />
                                                                            </c:if>
                                                                            <c:if test="${actionBean.petakKegunaanAksr[rowNo] eq '03'}">
                                                                                <c:set var="petakKegunaanAksrVal" value="Stor" />
                                                                            </c:if>

                                                                            <s:text name="petakKegunaanAksrVal"  value="${petakAksesori.kegunaan.nama}" readonly="readonly" size="25"/>

                                                                        </td>

                                                                        <td><c:set var="rowNo" value="${items}" />
                                                                            <s:text name="lokasiAksrVal"  value="Dalam Bangunan" readonly="readonly" />
                                                                    <tr>
                                                                        <c:if test="${not statusPA.last}">
                                                                            <!--<td>&nbsp;</td>-->
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>

                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                            <td>&nbsp;</td>
                                                                        </c:if>

                                                                        <c:if test="${statusPA.last}">
                                                                        </tr>

                                                                    </c:if>
                                                                    <c:set var="items3" value="${items3+1}"/>
                                                                </c:forEach>
                                                            </c:if>
                                                            </tr>
                                                            <c:if test="${not statusP.last}">
                                                                <td>&nbsp;</td>
                                                                <td>&nbsp;</td>
                                                                <td> <c:if test="${statusP.count eq 1}">
                                                                    </c:if>
                                                                </td>
                                                                <td>&nbsp;</td>  <%--Tingkat--%>
                                                            </c:if>

                                                            <c:set var="items" value="${items+1}"/>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>

                                                <c:if test="${fn:length(tgkt.senaraiPetak) == 0}">
                                                    <tr>
                                                        <td>&nbsp;</td> <%-- code added--%>
                                                        <td>&nbsp;</td>
                                                        <c:if test="true">
                                                            <td> </td>
                                                        </c:if>
                                                    </c:if>
                                                </tr>
                                                <c:if test="${not status.last}">
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </c:if>

                                                    <c:set var="items2" value="${items2+1}"/>

                                                </c:forEach>
                                            </c:if>  <%--added for Bangunan--%>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <br><br>
                            <div class="subtitle">
                                <fieldset class="aras1">
                                    <c:if test="${ekstrak}">
                                        <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
                                            <legend>Senarai Harta Bersama </legend><br>
                                            <display:table class="tablecloth" name="${actionBean.listpermohonanHartaBersama}" cellpadding="0" cellspacing="0" id="pbs">
                                                <display:column title="No. Harta Bersama"><center>${pbs.nama}</center> </display:column>
                                                <display:column title="Jenis Kegunaan"><center>${pbs.jenisHartaBersama.nama}</center></display:column>
                                            </display:table>
                                        </c:if>
                                    </c:if>
                                </fieldset>
                            </div>
                        </c:if>
                    </s:form>

