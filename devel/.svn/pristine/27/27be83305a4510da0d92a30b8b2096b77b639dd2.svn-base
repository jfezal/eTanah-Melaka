<%-- 
    Document   : maklumat_saksi
    Created on : Jan 27, 2011, 11:24:39 AM
    Author     : sitifariza.hanim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?saksiPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function removeSaksi(idSaksi){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?deleteSaksiOks&idSaksi='+idSaksi;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function popup(h){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?editSaksi&idSaksi='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_saksi?refreshpage';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }
    
    function addRecordSaksi(idOks){
        var idOp = $('#idOpBasedOnIdIP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?addRecordSaksiOks&idOp="+idOp+"&idOks="+idOks, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }
    
    function editSaksiOks(idSaksi){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?addRecordSaksiOks&idSaksi="+idSaksi, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=890,height=500, left=" + left + ",top=" + top);
    }
    
    function refreshPageSaksiOks(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?refreshPageSaksiOks';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }
    
    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
    
    function viewSaksi(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?viewSaksi&idSaksi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    
    function simpanSaksi(i){
        var idOks = $('#idOksPasukan'+i).val();
        var pasukan = $('#pasukan'+i).val();
        //alert("p:" +pasukan);
        if($('#pasukan').val()=="")
        {
            alert("Sila pilih maklumat pasukan dahulu.");
            $('#pasukan').focus();
            return false;
        }

        var idPasukan = $('#pasukan').val();

        $.get('${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?simpanSaksiPasukan&idPasukan='+pasukan+'&idOks='+idOks,
        function(html){
            $("#senaraiSaksiOpDiv").replaceWith($('#senaraiSaksiOpDiv', $(html)));
        }, 'html');
        document.getElementById("pasukan").value = "";
    }
    
    function checkExisting(idPasukan, i)
    {
        var idOks = $('#idOksPasukan'+i).val();
        //alert(idPasukan);
       

        if(idPasukan != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?checkExistingRecordSaksi&idPasukan='+idPasukan+'&idOks='+idOks,
            function(data){
                //alert(data);
                if(data == "exist"){
                    alert("Rekod saksi ini telah wujud. Sila pilih saksi yang lain.");
                    document.getElementById("pasukan"+i).value = "";
                }

            }, 'html');

        }
    }
    
    function removeSaksiPasukan(idPasukan){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?deleteSaksiDalaman&idPasukan='+idPasukan;
            $.get(url,
            function(data){
                $("#senaraiSaksiOpDiv").replaceWith($('#senaraiSaksiOpDiv', $(data)));
            },'html');}
    }

</script>
<s:form name="form1" id="form1"  beanclass="etanah.view.penguatkuasaan.KeteranganSaksiActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Saksi
            </legend>
            <c:if test="${!multipleOp}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk masukkan maklumat saksi
                </div>
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanSaksi}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="noPengenalan" title="No.Pengenalan"></display:column>
                    <display:column property="nama" title="Nama Saksi"></display:column>
                    <display:column title="Alamat">${line.alamat1}
                        <c:if test="${line.alamat2 ne null}"> , </c:if>
                        ${line.alamat2}
                        <c:if test="${line.alamat3 ne null}"> , </c:if>
                        ${line.alamat3}
                        <c:if test="${line.alamat4 ne null}"> , </c:if>
                        ${line.alamat4}
                        ${line.poskod}
                        ${line.negeri.nama}
                    </display:column>           
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksi('${line.idSaksi}');"/>
                        </div>
                    </display:column>

                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idSaksi}')"/>
                        </div>
                    </display:column>

                </display:table>
                <table>
                    <tr>
                        <td align="right">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${edit && multipleOp}">
                <div class="instr-fieldset"><br>
                    <font color="red">Makluman : </font> Klik butang tambah untuk masukkan maklumat saksi luar.

                </div>
                <div class="content">
                    <p>
                        <label>Id Operasi :</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi ne null}">
                            <u><a class="popup" onclick="viewRecordOP(${actionBean.operasiPenguatkuasaan.idOperasi})">${actionBean.operasiPenguatkuasaan.idOperasi}</a></u>
                        </c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Tarikh Laporan:</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>&nbsp;</c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Masa Laporan :</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}">
                            <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>
                            <fmt:formatDate pattern="aaa" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}" var="time"/>
                            <c:if test="${time eq 'AM'}">PAGI</c:if>
                            <c:if test="${time eq 'PM'}">PETANG</c:if>
                        </c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <br/>
                </div>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiOksIp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat OKS">
                            <u><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.nama}</a></u>
                        </display:column>
                        <display:column title="Maklumat Saksi">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(actionBean.senaraiPermohonanSaksi) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                        <th  width="1%" align="center"><b>No.Pengenalan</b></th>
                                        <th  width="5%" align="center" colspan="2"><b>Tindakan</b></th>

                                    </tr>
                                    <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksi">
                                        <c:if test="${line.idOrangKenaSyak eq saksi.aduanOrangKenaSyak.idOrangKenaSyak}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><u><a class="popup" onclick="viewSaksi(${saksi.idSaksi})">${saksi.nama}</a></u></td>
                                                <td width="50%">${saksi.noPengenalan}</td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Kemasikini untuk Saksi ${saksi.idSaksi}" onclick="editSaksiOks('${saksi.idSaksi}')"/>
                                                    </div>
                                                </td>
                                                <td width="5%">
                                                    <div align="center">
                                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" title="Hapus untuk Barang ${saksi.idSaksi}" onclick="removeSaksi('${saksi.idSaksi}');"/>
                                                    </div>
                                                </td>
                                            </tr>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>

                                    </c:forEach>
                                </table>
                            </c:if>

                        </display:column>
                        <display:column title="Tambah Saksi">
                            <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecordSaksi('${line.idOrangKenaSyak}');"/>
                        </display:column>
                    </display:table>
                    <s:hidden name="idOpBasedOnIdIP" id="idOpBasedOnIdIP"/>
                    <br><br>

                    <div class="instr-fieldset">
                        <font color="red">Makluman : </font>Sila klik butang simpan untuk masukkan maklumat saksi dari senarai saksi dalaman.
                    </div>&nbsp;<br/>
                    <br/>
                    <div id="senaraiSaksiOpDiv">
                        <display:table name="${actionBean.senaraiOksIp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat OKS" style="width:15%;">
                                <u><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.nama}</a></u>
                                <input type="hidden" nama="idOksPasukan${line_rowNum}" id="idOksPasukan${line_rowNum}" value="${line.idOrangKenaSyak}"/>
                            </display:column>
                            <display:column title="Senarai Saksi Dalaman" style="width:55%;">
                                <c:set value="1" var="count"/>

                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>No.Pengenalan</b></th>
                                        <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                        <th  width="5%" align="center"><b>Jawatan</b></th>
                                        <th  width="3%" align="center"><b>Hapus</b></th>

                                    </tr>

                                    <c:forEach items="${actionBean.senaraiPermohonanSaksiDalaman}" var="saksi">
                                        <c:if test="${line.idOrangKenaSyak eq saksi.aduanOrangKenaSyak.idOrangKenaSyak}">
                                            <c:set var="statusSaksiPerRow" value="exist"/>
                                            <tr>
                                                <td width="1%">${count}</td>
                                                <td width="5%">${saksi.noPengenalan}</td>
                                                <td width="5%">${saksi.nama}</td>
                                                <td width="5%">${saksi.alamat4}</td>
                                                <td width="3%">
                                                    <div align="center">
                                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSaksi('${saksi.idSaksi}');"/>
                                                    </div>
                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </display:column>
                            <display:column title="Tambah Saksi Dalaman">
                                <p>
                                    <s:select name="pasukan" id="pasukan${line_rowNum}" value="pasukan" onchange="checkExisting(this.value,'${line_rowNum}')">
                                        <s:option value=""> Sila Pilih </s:option>
                                        <c:forEach items="${actionBean.senaraiPasukan}" var="line">
                                            <s:option value="${line.idOperasiPenguatkuasaanPasukan}">${line.nama} - ${line.kodPerananOperasi.nama}
                                            </s:option>
                                        </c:forEach>
                                    </s:select>&nbsp;
                                    <s:button class="btn" onclick="if(simpanSaksi('${line_rowNum}'))doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                                </p> 
                            </display:column>
                        </display:table>
                    </div>


                </div>

            </c:if>

            <c:if test="${view && multipleOp}">
                <div class="content">
                    <p>
                        <label>Id Operasi :</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi ne null}">
                            <a class="popup" onclick="viewRecordOP(${actionBean.operasiPenguatkuasaan.idOperasi})">${actionBean.operasiPenguatkuasaan.idOperasi}</a>
                        </c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Tarikh Laporan:</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>&nbsp;</c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <p>
                        <label>Masa Laporan :</label>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}">
                            <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>
                            <fmt:formatDate pattern="aaa" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}" var="time"/>
                            <c:if test="${time eq 'AM'}">PAGI</c:if>
                            <c:if test="${time eq 'PM'}">PETANG</c:if>
                        </c:if>
                        <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                    </p>
                    <br/>
                </div>
                <legend>
                    Maklumat Saksi Luar
                </legend>
                <br>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiOksIp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat OKS">
                            <a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.nama}</a>
                        </display:column>
                        <display:column title="Maklumat Saksi">
                            <c:set value="1" var="count"/>
                            <c:if test="${fn:length(actionBean.senaraiPermohonanSaksi) ne 0}">
                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                        <th  width="1%" align="center"><b>No.Pengenalan</b></th>

                                    </tr>
                                    <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksi">
                                        <c:if test="${line.idOrangKenaSyak eq saksi.aduanOrangKenaSyak.idOrangKenaSyak}">
                                            <tr>
                                                <td width="5%">${count}</td>
                                                <td width="50%"><a class="popup" onclick="viewSaksi(${saksi.idSaksi})">${saksi.nama}</a></td>
                                                <td width="50%">${saksi.noPengenalan}</td>
                                            </tr>

                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>

                                    </c:forEach>
                                </table>
                            </c:if>

                        </display:column>
                    </display:table>
                    <br><br>
                    <legend>
                        Maklumat Saksi Dalaman
                    </legend>
                    <br><br>
                    <div id="senaraiSaksiOpDiv">
                        <display:table name="${actionBean.senaraiOksIp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true" style="width:5%;">${line_rowNum}</display:column>
                            <display:column title="Maklumat OKS" style="width:15%;">
                                <u><a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.nama}</a></u>
                                <input type="hidden" nama="idOksPasukan${line_rowNum}" id="idOksPasukan${line_rowNum}" value="${line.idOrangKenaSyak}"/>
                            </display:column>
                            <display:column title="Senarai Saksi Dalaman" style="width:55%;">
                                <c:set value="1" var="count"/>

                                <table width="100%" cellpadding="1">
                                    <tr>
                                        <th  width="1%" align="center"><b>Bil</b></th>
                                        <th  width="1%" align="center"><b>No.Pengenalan</b></th>
                                        <th  width="1%" align="center"><b>Nama Saksi</b></th>
                                        <th  width="5%" align="center"><b>Jawatan</b></th>

                                    </tr>

                                    <c:forEach items="${actionBean.senaraiPermohonanSaksiDalaman}" var="saksi">
                                        <c:if test="${line.idOrangKenaSyak eq saksi.aduanOrangKenaSyak.idOrangKenaSyak}">
                                            <c:set var="statusSaksiPerRow" value="exist"/>
                                            <tr>
                                                <td width="1%">${count}</td>
                                                <td width="5%">${saksi.noPengenalan}</td>
                                                <td width="5%">${saksi.nama}</td>
                                                <td width="5%">${saksi.alamat4}</td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </display:column>
                        </display:table>
                    </div>
                </div>


            </c:if>
        </fieldset>
    </div>

</s:form>
