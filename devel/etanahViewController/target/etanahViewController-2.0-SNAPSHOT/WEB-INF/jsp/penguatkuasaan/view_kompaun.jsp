<%--
    Document   : view_kompaun
    Created on : July 12, 2011, 11:33:22 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
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
    
    $(document).ready(function() {
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        //start: for senarai saksi
        for (var p = 0; p < bilOp; p++){
//            var bil =  document.getElementById('totalSaksi'+p).value;
//
//
//            //alert(bil);
//            if(bil != "0"){
//                for (var i = 0; i < bil; i++){
//                    var pilihStatus = document.getElementById('pilihStatus'+p+i).value;
//                    if(pilihStatus != ""){
//                        if(pilihStatus == "Y")
//                            document.getElementById("pilih"+p+i).checked = true;
//                    }else{
//                        document.getElementById("pilih"+p+i).checked = false;
//                    }
//                }
//            }
               
            //end for senarai saksi
            var bilKompaunIP =  document.getElementById('totalKompaunIP'+p).value;
            //alert(bilKompaunIP);
            var total = 0;
            if(bilKompaunIP != "0"){
                for (var i = 0; i < bilKompaunIP; i++){
                    var amaunIP = document.getElementById('amaunIP'+p+i).value;
                    if(amaunIP !=""){
                        //alert(amaunIP);
                        total += parseFloat(amaunIP);
                        document.getElementById('jumKompaun'+p).value=parseFloat(total).toFixed(2);
                    }
                }
            }
            
        }
             
        totalAmount();
    });



    function totalAmount(){
        var total = 0;
        var bilOp =  ${fn:length(actionBean.listOp)}; //list op
        for (var p = 0; p < bilOp; p++){
//            var bil =  document.getElementById('totalSaksi'+p).value;
//
//
//            for (var i = 0; i < bil; i++){
//                var amount = document.getElementById('kompaunSaksi'+p+i).value;
//                if(amount !=""){
//                    //alert(amount);
//                    total += parseFloat(amount);
//                    document.getElementById('kompaunSaksi'+p+i).value= parseFloat(amount).toFixed(2);
//                    document.getElementById('jumCaraBayar'+p).value=parseFloat(total).toFixed(2);
//                }
//            }
            
        }

    }
    function viewSyorKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewSyorKompaunDetail&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewMuktamadKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaun&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewMuktamadKompaunMelaka(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaunMelaka&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function refreshPageMuktamadkompaunMelakaOp(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshPageMuktamadkompaunMelakaOp';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function viewMuktamadKompaunOp(id,oks,idOp){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaunOp&idKompaun='+id+'&oks='+oks+'&idOp='+idOp;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }



</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean">
    <s:messages />
    <s:errors />
    <c:if test="${syorKompaun}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Syor Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewSyorKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${muktamadKompaun}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewMuktamadKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Muktamad Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${muktamadKompaunMLK}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewMuktamadKompaunMelaka('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Muktamad Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${bayaranPelupusan}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Muktamad Bayaran Pelupusan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewMuktamadKompaunMelaka('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                        </display:column>
                        <display:column title="Muktamad Bayaran Pelupusan (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>

                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${multipleOp}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true" style="width:50px;">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:300px;">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                        </td>
                                    </tr>

                                </table>
                        </display:column>
                        <display:column title="Maklumat Kompaun" style="width:500px;">
                            <c:set value="0" var="j"/>
                            <c:set var="jumKompaunPerRow" value="0"/>
                            <c:forEach items="${actionBean.senaraiKompaunIP}" var="k">
                                <c:if test="${k.orangKenaSyak.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                    <c:set var="jumKompaunPerRow" value="${fn:length(actionBean.senaraiKompaunIP)}"/>
                                </c:if>
                            </c:forEach>
                            <input type="hidden" name="totalKompaunIP${line_rowNum-1}" value="${jumKompaunPerRow}" id="totalKompaunIP${line_rowNum-1}">
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>No.Siri</b></th>
                                    <th  width="1%" align="center"><b>Diserahkan Kepada</b></th>
                                    <th  width="5%" align="center"><b>No.pengenalan</b></th>
                                    <th  width="5%" align="center"><b>Amaun (RM)</b></th>
                                </tr>
                                <c:forEach items="${actionBean.senaraiKompaun}" var="kompaun">
                                    <c:if test="${kompaun.orangKenaSyak.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                        <tr>
                                            <td width="5%">${j+1}</td>
                                            <td width="5%"><u><a class="popup" onclick="viewMuktamadKompaunOp('${kompaun.idKompaun}','${kompaun.orangKenaSyak.idOrangKenaSyak}','${line.idOperasi}')">${kompaun.noRujukan}</a></u></td>
                                        <td width="50%">${kompaun.orangKenaSyak.nama}</td>
                                        <td width="50%">
                                            <c:if test="${kompaun.noPengenalan ne null}">
                                                ${kompaun.noPengenalan}
                                            </c:if>
                                            <c:if test="${kompaun.noPengenalan eq null}">
                                                Tiada data
                                            </c:if>
                                        </td>
                                        <td width="30%">
                                            ${kompaun.amaun}
                                            <input type="hidden" name="amaunIP${line_rowNum-1}${j}" value="${kompaun.amaun}" id="amaunIP${line_rowNum-1}${j}">
                                        </td>
                                        </tr>
                                        <c:set value="${j+1}" var="j"/>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td colspan="4" align="right">Jumlah Bayaran(RM):</td>
                                    <td><input name="jumKompaun${line_rowNum-1}" value="0.00" id="jumKompaun${line_rowNum-1}" size="12" class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                </tr>
                            </table>

                        </display:column>

                    </display:table>
                    <br>
                    <%--
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426' || actionBean.permohonan.kodUrusan.kod eq '425'}">
                                            <legend>
                                                Senarai saksi luar
                                            </legend>

                        <div class="content" align="center">
                            <div id="senaraiSaksiLuarDiv">
                                <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:90%;">
                                    <display:column title="Bil" sortable="true" style="width:3%;">${line_rowNum}</display:column>
                                    <display:column title="Maklumat Laporan Operasi" style="width:23%">
                                        <table width="10%" cellpadding="1">
                                            <tr>
                                                <td width="3%"><font class="infoLP">Id Operasi :</font></td>
                                                <td width="3%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
                                            </tr>
                                            <tr>
                                                <td width="5%"><font class="infoLP">Tarikh laporan :</font></td>
                                                <td width="5%">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                            </tr>
                                            <tr>
                                                <td width="5%"><font class="infoLP">Masa laporan :</font></td>
                                                <td width="5%">
                                                    <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                                    <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                                    <c:if test="${time eq 'AM'}">PAGI</c:if>
                                                    <c:if test="${time eq 'PM'}">PETANG</c:if>
                                                </td>
                                            </tr>

                                        </table>
                                    </display:column>
                                    <display:column title="Senarai Saksi Luar" style="width:60%;">
                                        <c:set var="jumSaksi" value="0"/>
                                        <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                            <c:if test="${saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                <c:set var="jumSaksi" value="${fn:length(actionBean.senaraiPermohonanSaksi)}"/>
                                            </c:if>
                                        </c:forEach>
                                        <input type="hidden" name="totalSaksi${line_rowNum-1}" value="${jumSaksi}" id="totalSaksi${line_rowNum-1}">
                                        <p>
                                            <label>Tempoh (Hari) :</label>
                                            ${kompaun.tempohHari}
                                            <c:set var="tempohPerRow" value=""/>
                                            <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                                <c:choose>
                                                    <c:when test="${fn:length(saksiLuar.senaraiKompaun) ne 0}">
                                                        <c:forEach items="${saksiLuar.senaraiKompaun}" var="kompaun">
                                                            <c:if test="${kompaun.saksi.idSaksi eq saksiLuar.idSaksi && saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                                <c:set var="tempohPerRow" value="${kompaun.tempohHari}"/>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                            ${tempohPerRow}

                                        </p>
                                        <c:set value="0" var="count"/>

                                        <table width="100%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="1%" align="center"><b>Pilih</b></th>
                                                <th  width="10%" align="center"><b>No.Pengenalan</b></th>
                                                <th  width="20%" align="center"><b>Nama Saksi</b></th>
                                                <th  width="30%" align="center"><b>Alamat</b></th>
                                                <th  width="30%" align="center"><b>Muktamad Kompaun</b></th>
                                            </tr>
                                            <c:forEach items="${actionBean.senaraiPermohonanSaksi}" var="saksiLuar">
                                                <c:if test="${saksiLuar.operasiPenguatkuasaan.idOperasi eq line.idOperasi}">
                                                    <tr>
                                                        <td width="1%">${count+1}</td>
                                                        <td>
                                                            <s:checkbox name="pilih${line_rowNum-1}${count}" disabled="true" id="pilih${line_rowNum-1}${count}" value="${saksiLuar.idSaksi}"/>
                                                            <input type="hidden" name="pilihStatus${line_rowNum-1}${count}" value="${saksiLuar.statusDakwaanKompaun}" id="pilihStatus${line_rowNum-1}${count}">
                                                            <input type="hidden" name="pilihTemp${line_rowNum-1}${count}" value="${saksiLuar.idSaksi}" id="pilihTemp${line_rowNum-1}${count}">
                                                        </td>
                                                        <td width="10%">${saksiLuar.noPengenalan}</td>
                                                        <td width="20%"><a class="popup" onclick="viewSaksi(${saksiLuar.idSaksi})">${saksiLuar.nama}</a></td>
                                                        <td width="30%">
                                                            <font style="text-transform: uppercase">
                                                            <c:if test="${saksiLuar.alamat1 ne null}">${saksiLuar.alamat1}</c:if>
                                                            <c:if test="${saksiLuar.alamat2 ne null}">${saksiLuar.alamat2}</c:if>
                                                            <c:if test="${saksiLuar.alamat3 ne null}">${saksiLuar.alamat3}</c:if>
                                                            <c:if test="${saksiLuar.alamat4 ne null}">${saksiLuar.alamat4}</c:if>
                                                            <c:if test="${saksiLuar.poskod ne null}">${saksiLuar.poskod}</c:if>
                                                            <c:if test="${saksiLuar.negeri ne null}">${saksiLuar.negeri.nama}</c:if>
                                                            </font>
                                                        </td>
                                                        <td width="10%">RM &nbsp;
                                                            <c:choose>
                                                                <c:when test="${fn:length(saksiLuar.senaraiKompaun) ne 0}">
                                                                    <c:forEach items="${saksiLuar.senaraiKompaun}" var="kompaun">
                                                                        <c:if test="${kompaun.saksi.idSaksi eq saksiLuar.idSaksi}">
                                                                            ${kompaun.amaun}
                                                                            <s:hidden name="kompaunSaksi${line_rowNum-1}${count}" value="${kompaun.amaun}" formatPattern="0.00" id="kompaunSaksi${line_rowNum-1}${count}" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);"/>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>

                                                    </tr>
                                                </c:if>

                                                <c:set value="${count+1}" var="count"/>

                                            </c:forEach>
                                            <tr>
                                                <td colspan="5" align="right">Jumlah Bayaran(RM):</td>
                                                <td><input name="jumCaraBayar${line_rowNum-1}" value="0.00" id="jumCaraBayar${line_rowNum-1}" size="12" class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                            </tr>

                                        </table>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </c:if> --%>
                </div>
            </fieldset>
        </div>



    </c:if>
</s:form>
