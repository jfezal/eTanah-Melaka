<%--
    Document   : maklumat_jadualpetak
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
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

        window.open("${pageContext.request.contextPath}/strata/mlk/rmhs?showForm2&namaBangunan="+b+"&tingkat="+p+"&idTingkat="+idT, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=250");
    }

    function popupPetakAksesori(b,t,p){

        window.open("${pageContext.request.contextPath}/strata/mlk/rmhs?showForm3&idBangunan="+b+"&idTingkat="+t+"&idPetak="+p, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=200");
    }

    function removePtkAksr(val){

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/mlk/rmhs?deletePtkAksr&idAksesori='+val;
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
        var url = '${pageContext.request.contextPath}/strata/mlk/rmhs?selectedBangunan&idBangunan='+idBangunanVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function selectedTingkat(idBangunanVal,idTingkatVal){
        document.getElementById("tempIdTingkat").value = idTingkatVal;
        var url = '${pageContext.request.contextPath}/strata/mlk/rmhs?selectedTingkat&idBangunan='+idBangunanVal+'&idTingkat='+idTingkatVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function selectedPetak(idBangunanVal,idTingkatVal,idPetakVal){
        document.getElementById("tempIdPetak").value = idPetakVal;
        var url = '${pageContext.request.contextPath}/strata/mlk/rmhs?selectedPitak&idBangunan='+idBangunanVal+'&idTingkat='+idTingkatVal+"&idPetak="+idPetakVal;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function selectLPAK(idBangunanVal,idTingkatVal,idPetakVal){        
        //        alert("idBangunanVal:"+idBangunanVal);
        //        alert("idTingkatVal:"+idTingkatVal);
        //        alert("idPetakVal:"+idPetakVal);        
        document.getElementById("tempIdPetak").value = idPetakVal;        
        var url = '${pageContext.request.contextPath}/strata/mlk/rmhs?selectedPitak&idBangunan='+idBangunanVal+'&idTingkat='+idTingkatVal+"&idPetak="+idPetakVal;
        $.get(url,
        function(data){             
            $('#page_div').html(data);
        },'html');
    }
    
</script>
<table><tr><td>
            <s:form beanclass="etanah.view.strata.MaintainBangunanRMHSActionBean">
                <s:messages/>
                <s:errors/>

                <c:if test="${fn:length(actionBean.pBangunanL) > 0 || fn:length(actionBean.pBangunanLandParcel) > 0}">
                    <center><p><font color="blue"><b>Jumlah Unit Syer: <s:text name="mohonSkim.jumlahUnitSyer" readonly="true" size="8"/>
                                </b></font></p></center>
                    <br />
                </c:if>

                <c:if test="${fn:length(actionBean.pBangunanL) > 0}">                   
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>Senarai Jadual Petak-petak Bagi Bangunan </legend>                            
                            <p>
                            <table class="tablecloth" width="100%">
                                <tr>
                                    <th>Bangunan</th>
                                    <th></th>  <%--code added1--%>
                                    <th>Senarai Tingkat</th>
                                    <th></th>  <%--code added2--%>
                                    <th>Senarai Petak</th>
                                    <!--<th>Keluasan <br><font size="1">(M)</font>2</th>-->
                                    <th>Unit-unit Syer</th>
                                    <th>Jenis Kegunaan</th>
                                    <%--<th</th>--%>
                                    <th></th>
                                    <th></th>  <%--code added3--%>
                                    <th>Petak Aksesori</th>
                                    <th>Jenis Kegunaan</th>
                                    <th width="16%">Lokasi</th>
                                    <%-- <th></th>--%>
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
                                                    <%--added for Tingkat--%>
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

                                                    <c:if test="${actionBean.tempIdTingkat eq tgkt.idTingkat}">   <%--added for Tingkat--%>

                                                        <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                                            <td>${petak.nama}</td>
                                                            <%--<td><s:text name="petakluas"  size="10" readonly="readonly" value="${petak.luas}"/></td>--%>
                                                            <td><s:text name="petaksyer" size="12" readonly="readonly" value="${petak.syer}"/></td>
                                                            <td><s:text name="pk"  readonly="readonly" value="${petak.kegunaan.nama}" />  
                                                                <%-- code commented --%>                                                               
                                                            <td><%--<s:button  onclick="popupPetakAksesori('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" value="Tambah" name="tambah" class="btn" />--%></td>

                                                            <td>
                                                                <%-- ${fn:length(bgn.senaraiTingkat)},${bgn.idBangunan}--%>
                                                                <c:if test="${fn:length(petak.senaraiPetakAksesori) > 0}" >
                                                                    <c:if test="${actionBean.tempIdPetak ne petak.idPetak}">
                                                                        <div align="center">
                                                                            <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                                  onclick="selectedPetak('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">
                                                                        <div align="center">
                                                                            <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                                  onclick="selectedTingkat('${bgn.idBangunan}','${tgkt.idTingkat}');" onmouseover="this.style.cursor='pointer';" >
                                                                        </div>
                                                                    </c:if>

                                                                </c:if>
                                                            </td>

                                                            <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">   <%--added for Tingkat--%>

                                                                <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                                                    <td>${petakAksesori.nama}</td>
                                                                    <td>
                                                                        <%--<s:select name="petakKegunaanAksr[${items3}]">
                                                                               <s:option value="0">Sila Pilih</s:option>
                                                                               <s:options-collection collection="${actionBean.kGunaPetakAksesoriL}" label="nama" value="kod" />
                                                                           </s:select>--%>

                                                                        <c:set var="rowNo" value="${items}" />
                                                                        <%--${actionBean.petakKegunaanAksr[rowNo]}--%>
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
                                                                        <%--<s:text name="petakKegunaanAksrVal"  value="Tempat Letak Kenderaan" readonly="readonly" size="25" />--%>

                                                                        <%--<c:set var="rowNo" value="${items}" />
                                                                       ${actionBean.petakKegunaanAksr[rowNo]}

                                            <s:text name="petakKegunaanAksrVal"  value="${actionBean.petakKegunaanAksr[rowNo]}" readonly="readonly" />--%>

                                                                    </td>

                                                                    <td><c:set var="rowNo" value="${items}" />
                                                                        <%--${actionBean.petakKegunaan[rowNo]}--%>

                                                                        <%--<s:text name="lokasiAksrVal"  value="${petakAksesori.lokasi}" readonly="readonly" />--%>
                                                                        <s:text name="lokasiAksrVal"  value="Dalam Bangunan" readonly="readonly" />


                                                                        <%--<s:select name="lokasiAksr[${items3}]">
                                                                            <s:option value="0">Sila Pilih</s:option>
                                                                            <s:option value="Luar Bangunan">Luar Bangunan</s:option>
                                                                            <s:option value="Dalam Bangunan">Dalam Bangunan</s:option>
                                                                        </s:select>--%>

                                                                        <%--    <td>
                                                                                <c:if test="${status.last}">
                                                                                    <c:if test="${statusP.last}">
                                                                                        <c:if test="${statusPA.last}">
                                                                                            <div align="center">
                                                                                                <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                                                     onclick="removePtkAksr('${petakAksesori.idAksesori}')" onmouseover="this.style.cursor='pointer';" >
                                                                                            </div>
                                                                                        </c:if>
                                                                                    </c:if>
                                                                                </c:if>
                                                                            </td>--%>
                                                                <tr>
                                                                    <c:if test="${not statusPA.last}">
                                                                        <%--<td>&nbsp;</td>--%>
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
                                                                    <%--<s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin--%>
                                                                </c:if>
                                                            </td>
                                                            <td>&nbsp;</td>  <%--code added for Tingkat--%>
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
                                                        <td> <%--<s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" />--%></td>
                                                    </c:if> <%-- <td>
                                                          <c:if test="${status.last}">
                                                              <div align="center">
                                                                  <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                       onclick="removeTingkat('${tgkt.idTingkat}')" onmouseover="this.style.cursor='pointer';" >
                                                              </div>
                                                          </c:if>
                                                      </td>--%>
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
                        <c:if test="${fn:length(actionBean.pBangunanProvisionalBlock) > 0}">
                            <div class="subtitle">
                                <fieldset class="aras1">
                                    <legend>Senarai Jadual Petak-petak Bangunan Sementara</legend>
                                    <p>
                                    <table class="tablecloth" width="100%">
                                        <tr>
                                            <th>Bangunan</th>
                                            <th></th>  <%--code added1--%>
                                            <th>Senarai Tingkat</th>
                                            <th></th>  <%--code added2--%>
                                            <th>Senarai Petak</th>
                                            <%--<th>Keluasan <br><font size="1">(M)</font>2</th>--%>
                                            <th>Unit-unit Syer</th>
                                            <th>Jenis Kegunaan</th>
                                            <%--<th</th>--%>
                                            <th></th>
                                            <th></th>  <%--code added3--%>
                                            <th>Petak Aksesori</th>
                                            <th>Jenis Kegunaan</th>
                                            <th width="16%">Lokasi</th>
                                            <%-- <th></th>--%>
                                        </tr>
                                        <c:set var="items" value="0"/>
                                        <c:set var="items2" value="0"/>
                                        <c:set var="items3" value="0"/>

                                        <s:hidden name="tempIdBangunan" id="tempIdBangunan" value="" />
                                        <s:hidden name="tempIdTingkat" id="tempIdTingkat" value="" />
                                        <s:hidden name="tempIdPetak" id="tempIdPetak" value="" />

                                        <c:forEach items="${actionBean.pBangunanProvisionalBlock}" var="bgn" varStatus="statusB">

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
                                                            <%--added for Tingkat--%>
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

                                                            <c:if test="${actionBean.tempIdTingkat eq tgkt.idTingkat}">   <%--added for Tingkat--%>

                                                                <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                                                    <td>${petak.nama}</td>
                                                                    <%--<td><s:text name="petakluas"  size="10" readonly="readonly" value="${petak.luas}"/></td>--%>
                                                                    <td><s:text name="petaksyer" size="12" readonly="readonly" value="${petak.syer}"/></td>
                                                                    <td><s:text name="pk"  readonly="readonly" value="${petak.kegunaan.nama}" />
                                                                        <%-- code commented --%>
                                                                    <td><%--<s:button  onclick="popupPetakAksesori('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" value="Tambah" name="tambah" class="btn" />--%></td>

                                                                    <td>
                                                                        <%-- ${fn:length(bgn.senaraiTingkat)},${bgn.idBangunan}--%>
                                                                        <c:if test="${fn:length(petak.senaraiPetakAksesori) > 0}" >
                                                                            <c:if test="${actionBean.tempIdPetak ne petak.idPetak}">
                                                                                <div align="center">
                                                                                    <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                                          onclick="selectedPetak('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                                                                </div>
                                                                            </c:if>
                                                                            <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">
                                                                                <div align="center">
                                                                                    <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                                          onclick="selectedTingkat('${bgn.idBangunan}','${tgkt.idTingkat}');" onmouseover="this.style.cursor='pointer';" >
                                                                                </div>
                                                                            </c:if>

                                                                        </c:if>
                                                                    </td>

                                                                    <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">   <%--added for Tingkat--%>

                                                                        <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                                                            <td>${petakAksesori.nama}</td>
                                                                            <td>
                                                                                <%--<s:select name="petakKegunaanAksr[${items3}]">
                                                                                       <s:option value="0">Sila Pilih</s:option>
                                                                                       <s:options-collection collection="${actionBean.kGunaPetakAksesoriL}" label="nama" value="kod" />
                                                                                   </s:select>--%>

                                                                                <c:set var="rowNo" value="${items}" />
                                                                                <%--${actionBean.petakKegunaanAksr[rowNo]}--%>
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
                                                                                <%--<s:text name="petakKegunaanAksrVal"  value="Tempat Letak Kenderaan" readonly="readonly" size="25" />--%>

                                                                                <%--<c:set var="rowNo" value="${items}" />
                                                                               ${actionBean.petakKegunaanAksr[rowNo]}

                                            <s:text name="petakKegunaanAksrVal"  value="${actionBean.petakKegunaanAksr[rowNo]}" readonly="readonly" />--%>

                                                                            </td>

                                                                            <td><c:set var="rowNo" value="${items}" />
                                                                                <%--${actionBean.petakKegunaan[rowNo]}--%>

                                                                                <%--<s:text name="lokasiAksrVal"  value="${petakAksesori.lokasi}" readonly="readonly" />--%>
                                                                                <s:text name="lokasiAksrVal"  value="Dalam Bangunan" readonly="readonly" />


                                                                                <%--<s:select name="lokasiAksr[${items3}]">
                                                                                    <s:option value="0">Sila Pilih</s:option>
                                                                                    <s:option value="Luar Bangunan">Luar Bangunan</s:option>
                                                                                    <s:option value="Dalam Bangunan">Dalam Bangunan</s:option>
                                                                                </s:select>--%>

                                                                                <%--    <td>
                                                                                        <c:if test="${status.last}">
                                                                                            <c:if test="${statusP.last}">
                                                                                                <c:if test="${statusPA.last}">
                                                                                                    <div align="center">
                                                                                                        <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                                                             onclick="removePtkAksr('${petakAksesori.idAksesori}')" onmouseover="this.style.cursor='pointer';" >
                                                                                                    </div>
                                                                                                </c:if>
                                                                                            </c:if>
                                                                                        </c:if>
                                                                                    </td>--%>
                                                                        <tr>
                                                                            <c:if test="${not statusPA.last}">
                                                                                <%--<td>&nbsp;</td>--%>
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
                                                                            <%--<s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin--%>
                                                                        </c:if>
                                                                    </td>
                                                                    <td>&nbsp;</td>  <%--code added for Tingkat--%>
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
                                                                <td> <%--<s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" />--%></td>
                                                            </c:if> <%-- <td>
                                                                  <c:if test="${status.last}">
                                                                      <div align="center">
                                                                          <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                               onclick="removeTingkat('${tgkt.idTingkat}')" onmouseover="this.style.cursor='pointer';" >
                                                                      </div>
                                                                  </c:if>
                                                              </td>--%>
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
                                </fieldset>
                            </div>
                        </c:if>
                        <br><br>                        

                        <%--List For LandParcels--%>
                        <br /><br />
                        <c:if test="${fn:length(actionBean.pBangunanLandParcel) > 0}">
                            <s:hidden name="tempIdPetak" id="tempIdPetak" value="" />
                            <div class="subtitle">
                                <fieldset class="aras1">
                                    <legend>Senarai Jadual Petak-petak Tanah</legend>
                                    <p>
                                    <table class="tablecloth" width="100%">
                                        <tr>
                                            <th>Land Parcel</th>
                                            <th>Unit-unit Syer</th>
                                            <th></th>
                                            <th>Petak Aksesori</th>
                                            <th>Jenis Kegunaan</th>
                                            <th width="16%">Lokasi</th>
                                        </tr>

                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.pBangunanLandParcel}" var="bgn" varStatus="statusB">
                                            <tr>
                                                <td><center>${bgn.nama}</center></td>
                                                <td><center>${bgn.syerBlok}</center></td>
                                                <td>
                                                    <c:forEach items="${bgn.senaraiTingkat}" var="tgkt" varStatus="status"> 
                                                        <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">   
                                                            <c:if test="${fn:length(petak.senaraiPetakAksesori) > 0}" >
                                                                <c:if test="${actionBean.tempIdPetak ne petak.idPetak}">
                                                                    <div align="center">
                                                                        <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                                              onclick="selectLPAK('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">
                                                                    <div align="center">
                                                                        <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/kurang.png' class='rem'
                                                                              onclick="selectLPAK('${bgn.idBangunan}','${tgkt.idTingkat}');" onmouseover="this.style.cursor='pointer';" >
                                                                    </div>
                                                                </c:if>

                                                            </c:if>

                                                            <c:if test="${actionBean.tempIdPetak eq petak.idPetak}">   <%--added for Tingkat--%>

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
                                                                    <%--<s:text name="petakKegunaanAksrVal"  value="Tempat Letak Kenderaan" readonly="readonly" size="25" />--%>
                                                                </td>

                                                                <td><c:set var="rowNo" value="${items}" />
                                                                    <%--<s:text name="lokasiAksrVal"  value="${petakAksesori.lokasi}" readonly="readonly" />--%>
                                                                    <s:text name="lokasiAksrVal"  value="Dalam Bangunan" readonly="readonly" />                                                                        
                                                            <tr>
                                                                <c:if test="${not statusPA.last}">
                                                                    <%--<td>&nbsp;</td>--%>

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
                                                </c:forEach>                    
                                            </c:forEach>           
                                            </td>

                                            </tr>
                                        </c:forEach>
                                    </table> </fieldset></div>
                                </c:if>

                        <br /><br />
                        <div class="subtitle">
                            <fieldset class="aras1">                                                                   
                                <legend>Senarai Harta Bersama </legend><br>
                                <display:table class="tablecloth" name="${actionBean.listpermohonanHartaBersama}" cellpadding="0" cellspacing="0" id="pbs">
                                    <display:column title="No. Harta Bersama"><center>${pbs.nama}</center> </display:column>
                                    <display:column title="Jenis Kegunaan"><center>${pbs.jenisHartaBersama.nama}</center></display:column>
                                </display:table> 
                            </fieldset>
                        </div>
                    </s:form>
