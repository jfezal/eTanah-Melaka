<%-- 
    Document   : maklumat_pemohon
    Created on : 19-Jan-2011, 11:01:49
    Author     : nordiyana
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">

    var DELIM = "__^$__";

    function doUpperCase(id) {
        var val = $('#' + id).val().toUpperCase();
        $('#' + id).val(val);
    }

   

  

     function deletePengadu(val, idPermohonan) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?deletePengadu&idPemohon=' + val + '&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function editPenerima(i, value) {

        var d;
        if (value == 'penerima') {
            d = $('.a' + i).val();
        }
        else if (value == 'gadaian') {
            d = $('.a2' + i).val();
        }
        window.open("${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?showEditPenerima&idPihak=" + d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=700,scrollbars=yes");
    }

    //fikri : automatic insert into table for syer


    function refreshPagePemohon() {
        var url = '${pageContext.request.contextPath}/pengambilan/pengambilan_pemohon?refreshpage';
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                }, 'html');
    }

    function reloadTuanTanah(val) {
//        alert("val" + val);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?reloadTuanTanah&idMohonHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function simpanTuanTanah(val, idPermohonan) {
//        alert("idHakmilikPihak " + val);
//        alert("idPermohonan " + idPermohonan);
//        alert("idMohonHakmilik" + idMohonHakmilik);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?simpanTuanTanah&idHakmilikPihak=' + val + '&idPermohonan=' + idPermohonan;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function selectHakmilik(val) {
//        alert("val" + val);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/semak/pemohon_aduan?reloadMain&idMohonHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

  



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.semakan.pemohonAduanActionBean">
    <div class="subtitle displaytag">
        <s:messages/>
        <s:errors/>
        <s:hidden name="kod" id="kod"/>
        <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
        <fieldset class="aras1" align="center">
            <legend>
            </legend>
            <br>
            <div align="center">
                <s:select name="idMohonHakmilik" onchange="selectHakmilik(this.value);" id="idMohonHakmilik">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">                         
                        <c:if test="${item.hakmilik.idHakmilik ne null}">                              
                            <s:option value="${item.id}" style="width:400px">
                                ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                            </s:option>
                        </c:if>
                        <c:if test="${item.hakmilik.idHakmilik eq null}">
                            <s:option value="${item.id}" style="width:400px">
                                ${item.lot.nama} - ${item.noLot}
                            </s:option>
                        </c:if>
                    </c:forEach>
                </s:select>
                <c:if test="${actionBean.hakmilikPermohonan ne null}">
                    <br><br>
                    Adakah Pengadu adalah Tuan Tanah ? : <s:radio name="maklumatPenyerah" id="maklumatPenyerah" checked="checked" value="idMohonHakmilik" onclick="reloadTuanTanah(${actionBean.hakmilikPermohonan.id});"/> Ya 

                </c:if>
            </div>
            <c:if test="${fn:length(actionBean.senaraiHakmilikPihak) > 0 }">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPihak}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idHakmilikPihakBerkepentingan}"/>
                        </display:column>
                        <display:column property="nama" title="Nama" />
                        <display:column property="noPengenalan" title="No.Pengenalan" />
                        <display:column property="bangsa.nama" title="Bangsa" />
                        <display:column  title="Syer" >
                            ${line.jumlahPenyebut} /  ${line.jumlahPembilang}
                        </display:column>
                        <display:column title="Pilih">
                            <p align="center">
                                <s:radio name="maklumatPenyerah" id="maklumatPenyerah" checked="checked" value="Y" onclick="simpanTuanTanah('${line.idHakmilikPihakBerkepentingan}','${actionBean.permohonan.idPermohonan}');"/> Ya &nbsp
                            </p>
                        </display:column>
                        <%--</c:if>--%>
                    </display:table>
                </div>
            </c:if>


            <br>
            <fieldset class="aras1">
                <c:if test="${fn:length(actionBean.pemohonList) > 0 }">
                    <c:if test="${actionBean.hakmilikPermohonan ne null}">
                        <legend>
                            Senarai Pengadu 
                        </legend>

                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                                <display:column title="Bil" sortable="true">${line_rowNum}
                                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                </display:column>
                                <display:column  title="Nama" >
                                    <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.pihak.nama}
                                </display:column>
                                <display:column  title="hakmilik" >
                                    <%--<a href="#" onclick="viewPihak('${line.pihak.idPihak}', 'pemohon');return false;">--%>${line.hakmilik.idHakmilik}
                                </display:column>
                                <display:column property="pihak.noPengenalan" title="No. Pengenalan/No.Syarikat" />${line.pihak.noPengenalan}
                                <display:column title="Alamat ">${line.pihak.alamat1}
                                    <c:if test="${line.pihak.alamat2 ne null}">,</c:if>
                                    ${line.pihak.alamat2}
                                    <c:if test="${line.pihak.alamat3 ne null}"> </c:if>
                                    ${line.pihak.alamat3}
                                    <c:if test="${line.pihak.alamat4 ne null}">,</c:if>
                                    ${line.pihak.alamat4}
                                    <c:if test="${line.pihak.poskod ne null}">,</c:if>
                                    ${line.pihak.poskod}
                                    <c:if test="${line.pihak.negeri.kod ne null}">,
                                        <c:if test="${line.pihak.negeri.kod eq '01'}">JOHOR</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '02'}">KEDAH</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '03'}">KELANTAN</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '04'}">MELAKA</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '05'}">NEGERI SEMBILAN</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '06'}">PAHANG</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '07'}">PULAU PINANG</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '08'}">PERAK</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '09'}">PERLIS</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '10'}">SELANGOR</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '11'}">TERENGGANU</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '12'}">SABAH</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '13'}">SARAWAK</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '14'}">WP KUALA LUMPUR</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '15'}">WP LABUAN</c:if>
                                        <c:if test="${line.pihak.negeri.kod eq '16'}">WP PUTRAJAYA</c:if>
                                    </c:if>
                                    <%--<font style="text-transform:uppercase;">${line.pihak.negeri.nama}</font>--%>
                                </display:column>
                                <%--<c:if test="${edit}">--%>
                                <display:column title="Hapus">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                             onclick="deletePengadu('${line.idPemohon}','${actioanBean.permohonan.idPermohonan}');
                                                     return false;" onmouseover="this.style.cursor = 'pointer';">
                                    </p>
                                </display:column>
                                <%--</c:if>--%>
                            </display:table>
                        </div>
                        <%--</c:if>--%>


                        <br/>
                    </c:if>
                </c:if>

            </fieldset>
        </fieldset>

        <br/>
        <fieldset class="aras1">
            <c:if test="${actionBean.hakmilikPermohonan ne null}">
                <legend>Maklumat Pengadu Selain Tuan Tanah</legend>

                <p>
                    <label>Nama</label><s:text name="penyerahNama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
                </p>
                <p>
                    <label><em>*</em>No. Pengenalan :</label>

                    <s:select name="kodJenisPengenalan" id="kodJenisPengenalan" value="${actionBean.permohonan.penyerahJenisPengenalan.kod}">
                        <s:option value="">Pilih Jenis...</s:option>
                        <s:option value="B">No K/P Baru  </s:option>
                        <s:option value="S">No Syarikat  </s:option>
                        <s:option value="L">No K/P Lama  </s:option>
                        <s:option value="P">No Pasport  </s:option>
                        <s:option value="T">No Tentera  </s:option>
                        <s:option value="I">No Polis  </s:option>
                        <s:option value="0">Tidak Berkenaan  </s:option>
                        <s:option value="N">No Bank  </s:option>
                        <s:option value="F">No Paksa  </s:option>
                        <s:option value="U">No Pertubuhan  </s:option>
                        <s:option value="D">No Pendaftaran  </s:option>
                        <s:option value="Z">Badan Kerajaan  </s:option>
                        <s:option value="X">Tiada  </s:option>
                    </s:select>

                    <s:hidden name="penyerahNoPengenalan" id="penyerahNoPengenalan" value="${actionBean.penyerahNoPengenalan}"/>
                    <s:text name="pNoPengenalan" id="penyerahNoPengenalan" size="10" value="${actionBean.permohonan.penyerahNoPengenalan}"/>
                    <em>[cth: 780901057893]</em>
                    <%--<input type=button name="searchPenyerah" value="Cari" class="btn" id="carianPihak"
                           onclick="javascript:populatePenyerah(this);" />--%>
                </p>
                <p>
                    <label><em>*</em>Alamat</label>
                    <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>&nbsp;</label>
                    <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Bandar</label>
                    <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
                </p>

                <p>
                    <label>Poskod</label>
                    <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
                    <em>5 Digit [cth : 12000]</em>
                </p>

                <p>
                    <label><em>*</em>Negeri</label>
                    <%--penyerahNegeri.kod--%>
                    <s:select name="penyerahNegeri" id="penyerahNegeri" >
                        <s:option value="0">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                    <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
                </p>
                <p>
                    <label>Jenis Pihak Kepentingan</label>
                    <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
                </p>
                <br/>
                <p align="center">
                    <s:button name="simpanPengadu" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>  
                </p>
            </c:if>

    </div>

</fieldset>

<br>

</s:form>



