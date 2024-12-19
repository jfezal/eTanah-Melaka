<%--
    Document   : tawaran_kompaun
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<c:if test="${multipleOp || viewMultipleOp}">
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
        var bil =  ${fn:length(actionBean.senaraiBarangRampasan)};
        for (var i = 0; i < bil; i++){
            if($("#dakwaBarang"+i).val() == "Y"){
                document.getElementById("pilihBarang"+i).checked = true;
            }
        }

        var totalRecord =  ${fn:length(actionBean.senaraiAduanOrangKenaSyak)};
        for (var i = 0; i < totalRecord; i++){
            if($("#dakwaOks"+i).val() == "Y"){
                document.getElementById("pilihOks"+i).checked = true;
            }
        }
        
    <c:if test="${multipleOp || viewMultipleOp}">
            //for multipule op
            var bilOp =  ${fn:length(actionBean.listOp)}; //list op
            for (var p = 0; p < bilOp; p++){
                //alert("bil : "+bilOp);
                var listOks = ${fn:length(actionBean.senaraiOksForDakwa)};
                //                alert("senaraiOksForDakwa : "+listOks);
                for (var b = 0; b < listOks; b++){
                    //alert($("#OpDakwaBarang"+p+b).val());
                    if($("#OpDakwaOks"+p+b).val() == "Y"){
                        document.getElementById("pilihCheckBox"+p+b).checked = true;
                        //                        document.getElementById("pilihCheckBox"+p+b).value = "on";
                    }  
                }
            }
    </c:if>
    
            
        });


        function validate(){
            var totalRecord =  ${fn:length(actionBean.senaraiAduanOrangKenaSyak)};
            var a = 0;

            for (var k = 0; k < totalRecord; k++){

                var pilihOks = document.getElementById('pilihOks'+k).checked;

                if( pilihOks == true){
                    a++;
                }

            }
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '428'}">
            var bil =  ${fn:length(actionBean.senaraiBarangRampasan)};
            var j = 0;

            for (var i = 0; i < bil; i++){

                var pilih = document.getElementById('pilihBarang'+i).checked;

                if( pilih == true){
                    j++;
                }

            }
            
            if( j==0 && a==0 ){
                alert("Sila pilih orang yang hendak di dakwa");
                return false;
            }
    </c:if>
           

            if(a==0 ){
                alert("Sila pilih orang yang hendak di dakwa");
                return false;
            }


            return true;

        }

        function viewOKS(id){
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
        }


        function validateDakwa(){
            var bilOp =  ${fn:length(actionBean.listOp)}; //list op
            //alert("bilOp : "+bilOp);

            var bilPilihOks= 0;

            for (var i = 0; i < bilOp; i++){
                //check for list barang
                var listOks = ${fn:length(actionBean.senaraiOksForDakwa)};
                for (var b = 0; b < listOks; b++){
                    //alert("p[id operasi] : "+i +". b[row] :"+b);
                    var pilihCheckBox = document.getElementById('pilihCheckBox'+i+b).checked;
                    //alert("pilih barang untuk id op "+i+" :"+pilihBarangDakwa);

                    if( pilihCheckBox == true){
                        bilPilihOks++;
                    }

                }
            }

            //alert("bilBarang : "+bilBarang);

            if(bilPilihOks==0 ){
                alert("Sila pilih orang yang hendak di dakwa");
                return false;
            }

            return true;

        }
        


</script>
<s:form beanclass="etanah.view.penguatkuasaan.SenaraiDakwaActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <c:if test="${edit}">
            <fieldset class="aras1">
                <legend>
                    Senarai Orang Kena Dakwa
                </legend>
                <br>
                <div class="instr-fieldset">
                    Sila tanda pada ruangan dakwa untuk memilih orang yang hendak didakwa.
                </div>&nbsp;

                <br/>
                <br/>
                <c:if test = "${actionBean.kodNegeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                        <legend>
                            Maklumat Pemunya Barang Rampasan
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Dakwa">
                                    <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}"/>
                                    <s:hidden name="senaraiBarangRampasan[${line_rowNum-1}].statusDakwaan" value="${line.statusDakwaan}" id="dakwaBarang${line_rowNum-1}"/>
                                </display:column>
                                <display:column title="Nama" property="namaPemunya"/>
                                <display:column title="Kategori Pengenalan">
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama eq null}">Tiada data</c:if>
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama ne null}">${line.kodJenisPengenalanPemunya.nama}</c:if>
                                </display:column>
                                <display:column title="No.Kad Pengenalan">
                                    <c:if test="${line.noPengenalanPemunya eq null}">Tiada data</c:if>
                                    <c:if test="${line.noPengenalanPemunya ne null}">${line.noPengenalanPemunya}</c:if>
                                </display:column>
                            </display:table>

                        </div>
                    </c:if>
                </c:if>

                <c:if test = "${actionBean.kodNegeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                        <legend>
                            Maklumat Pemunya Barang Rampasan
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Dakwa">
                                    <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}"/>
                                    <s:hidden name="senaraiBarangRampasan[${line_rowNum-1}].statusDakwaan" value="${line.statusDakwaan}" id="dakwaBarang${line_rowNum-1}"/>
                                </display:column>
                                <display:column title="Nama" property="namaPemunya"/>
                                <display:column title="Kategori Pengenalan">
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama eq null}">Tiada data</c:if>
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama ne null}">${line.kodJenisPengenalanPemunya.nama}</c:if>
                                </display:column>
                                <display:column title="No.Kad Pengenalan">
                                    <c:if test="${line.noPengenalanPemunya eq null}">Tiada data</c:if>
                                    <c:if test="${line.noPengenalanPemunya ne null}">${line.noPengenalanPemunya}</c:if>
                                </display:column>
                            </display:table>

                        </div>
                    </c:if>
                </c:if>

                <br>

                <legend>
                    Maklumat Orang Kena Syak
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line1" >
                        <display:column title="Dakwa">
                            <s:checkbox name="pilihOks${line1_rowNum-1}" id="pilihOks${line1_rowNum-1}" value="${line1.idOrangKenaSyak}"/>
                            <s:hidden name="senaraiAduanOrangKenaSyak[${line1_rowNum-1}].statusDakwaan" value="${line1.statusDakwaan}" id="dakwaOks${line1_rowNum-1}"/>
                        </display:column>
                        <display:column title="Nama" property="nama"/>
                        <display:column title="Kategori Pengenalan">
                            <c:if test="${line1.kodJenisPengenalan.nama eq null}">Tiada data</c:if>
                            <c:if test="${line1.kodJenisPengenalan.nama ne null}">${line1.kodJenisPengenalan.nama}</c:if>
                        </display:column>
                        <display:column title="No.Kad Pengenalan">
                            <c:if test="${line1.noPengenalan eq null}">Tiada data</c:if>
                            <c:if test="${line1.noPengenalan ne null}">${line1.noPengenalan}</c:if>
                        </display:column>
                    </display:table>
                </div>
                <div>
                    <p align="center">
                        <s:button class="btn" value="Simpan" name="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${view}">
            <fieldset class="aras1">
                <legend>
                    Senarai Orang Kena Dakwa
                </legend>
                <br/>
                <br/>
                <c:if test = "${actionBean.kodNegeri eq '04'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '422' || actionBean.permohonan.kodUrusan.kod eq '423' || actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '425A' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                        <legend>
                            Maklumat Pemunya Barang Rampasan
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Dakwa">
                                    <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}" disabled="true"/>
                                    <s:hidden name="senaraiBarangRampasan[${line_rowNum-1}].statusDakwaan" value="${line.statusDakwaan}" id="dakwaBarang${line_rowNum-1}"/>
                                </display:column>
                                <display:column title="Nama" property="namaPemunya"/>
                                <display:column title="Kategori Pengenalan">
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama eq null}">Tiada data</c:if>
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama ne null}">${line.kodJenisPengenalanPemunya.nama}</c:if>
                                </display:column>
                                <display:column title="No.Kad Pengenalan">
                                    <c:if test="${line.noPengenalanPemunya eq null}">Tiada data</c:if>
                                    <c:if test="${line.noPengenalanPemunya ne null}">${line.noPengenalanPemunya}</c:if>
                                </display:column>
                            </display:table>

                        </div>
                    </c:if>
                </c:if>

                <c:if test = "${actionBean.kodNegeri eq '05'}">
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '425' || actionBean.permohonan.kodUrusan.kod eq '426'}">
                        <legend>
                            Maklumat Pemunya Barang Rampasan
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Dakwa">
                                    <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}" disabled="true"/>
                                    <s:hidden name="senaraiBarangRampasan[${line_rowNum-1}].statusDakwaan" value="${line.statusDakwaan}" id="dakwaBarang${line_rowNum-1}"/>
                                </display:column>
                                <display:column title="Nama" property="namaPemunya"/>
                                <display:column title="Kategori Pengenalan">
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama eq null}">Tiada data</c:if>
                                    <c:if test="${line.kodJenisPengenalanPemunya.nama ne null}">${line.kodJenisPengenalanPemunya.nama}</c:if>
                                </display:column>
                                <display:column title="No.Kad Pengenalan">
                                    <c:if test="${line.noPengenalanPemunya eq null}">Tiada data</c:if>
                                    <c:if test="${line.noPengenalanPemunya ne null}">${line.noPengenalanPemunya}</c:if>
                                </display:column>
                            </display:table>

                        </div>
                    </c:if>
                </c:if>

            </fieldset>

            <fieldset class="aras1">
                <legend>
                    Maklumat Orang Kena Syak
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAduanOrangKenaSyak}" cellpadding="0" cellspacing="0" id="line1" >
                        <display:column title="Dakwa">
                            <s:checkbox name="pilihOks${line1_rowNum-1}" id="pilihOks${line1_rowNum-1}" value="${line1.idOrangKenaSyak}" disabled="true"/>
                            <s:hidden name="senaraiAduanOrangKenaSyak[${line1_rowNum-1}].statusDakwaan" value="${line1.statusDakwaan}" id="dakwaOks${line1_rowNum-1}"/>
                        </display:column>
                        <display:column title="Nama" property="nama"/>
                        <display:column title="Kategori Pengenalan">
                            <c:if test="${line1.kodJenisPengenalan.nama eq null}">Tiada data</c:if>
                            <c:if test="${line1.kodJenisPengenalan.nama ne null}">${line1.kodJenisPengenalan.nama}</c:if>
                        </display:column>
                        <display:column title="No.Kad Pengenalan">
                            <c:if test="${line1.noPengenalan eq null}">Tiada data</c:if>
                            <c:if test="${line1.noPengenalan ne null}">${line1.noPengenalan}</c:if>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${multipleOp}">
            <fieldset class="aras1">
                <legend>
                    Senarai Orang Kena Dakwa
                </legend>
                <br>
                <div class="instr-fieldset">
                    <font color="red">MAKLUMAN:</font>Sila tanda pada ruangan dakwa untuk memilih orang yang hendak didakwa.
                </div>&nbsp;

                <br/>
                <legend>
                    Maklumat Orang Kena Syak/Suspek Barang Rampasan
                </legend>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="lineOp" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${lineOp_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:250px;">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${lineOp.idOperasi})">${lineOp.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${lineOp.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${lineOp.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${lineOp.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Rampasan">
                            <c:set value="0" var="j"/>
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Pilih</b></th>
                                    <th  width="5%" align="center"><b>Nama Suspek</b></th>
                                    <th  width="5%" align="center"><b>No.Pengenalan</b></th>
                                    <th  width="5%" align="center"><b>Item</b></th>

                                </tr>
                                <c:forEach items="${actionBean.senaraiOksForDakwa}" var="oks">
                                    <tr>
                                        <td width="5%">${j+1}</td>
                                        <td width="5%">
                                            <!--<input type="checkbox" name="pilihCheckBox${lineOp_rowNum-1}${j}" id="pilihCheckBox${lineOp_rowNum-1}${j}" value="off" onclick="checkValue('${lineOp_rowNum-1}${j}');">-->
                                            <input type="checkbox" name="pilihCheckBox${lineOp_rowNum-1}${j}" id="pilihCheckBox${lineOp_rowNum-1}${j}" value="${oks.idOrangKenaSyak}">
                                            <input type="hidden" name="pilihOksDakwa${lineOp_rowNum-1}${j}" id="pilihOksDakwa${lineOp_rowNum-1}${j}" value="${oks.idOrangKenaSyak}">
                                            <input type="hidden" size="2" id="OpDakwaOks${lineOp_rowNum-1}${j}" value="${oks.statusDakwaan}">
                                        </td>

                                        <td width="30%">${oks.nama}</td>
                                        <td width="30%">
                                            <c:if test="${oks.noPengenalan eq null}">Tiada data</c:if>
                                            <c:if test="${oks.noPengenalan ne null}">${oks.noPengenalan}</c:if>
                                        </td>
                                        <td width="50%">
                                            <c:set value="1" var="b"/>
                                            <c:forEach items="${actionBean.senaraiBarangRampasanForKenderaan}" var="barang">
                                                <c:if test="${barang.aduanOrangKenaSyak.idOrangKenaSyak eq oks.idOrangKenaSyak}">
                                                    ${b} . ${barang.item} <br>
                                                    <c:set value="${b+1}" var="b"/>
                                                </c:if>
                                            </c:forEach>

                                        </td>
                                    </tr>
                                    <c:set value="${j+1}" var="j"/>
                                </c:forEach>
                            </table>
                        </display:column>


                    </display:table>
                </div>



                <div>
                    <p align="center">
                        <s:button class="btn" value="Simpan" name="simpanOKSForDakwa" onclick="if(validateDakwa())doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </div>
            </fieldset>
        </c:if>
        <c:if test="${viewMultipleOp}">
            <fieldset class="aras1">
                <legend>
                    Senarai Orang Kena Dakwa
                </legend>
                <br>
                <br/>
                <legend>
                    Maklumat Orang Kena Syak & Pemunya Barang Rampasan
                </legend>
                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="lineOp" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${lineOp_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:250px;">
                            <table width="10%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${lineOp.idOperasi})">${lineOp.idOperasi}</a></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${lineOp.tarikhOperasi}"/></td>
                                </tr>
                                <tr>
                                    <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                    <td width="20%">
                                        <fmt:formatDate pattern="hh:mm" value="${lineOp.tarikhOperasi}"/>
                                        <fmt:formatDate pattern="aaa" value="${lineOp.tarikhOperasi}" var="time"/>
                                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                                    </td>
                                </tr>

                            </table>
                        </display:column>
                        <display:column title="Maklumat Barang Rampasan">
                            <c:set value="0" var="j"/>
                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Pilih</b></th>
                                    <th  width="5%" align="center"><b>Nama Suspek</b></th>
                                    <th  width="5%" align="center"><b>No.Pengenalan</b></th>
                                    <th  width="5%" align="center"><b>Item</b></th>

                                </tr>
                                <c:forEach items="${actionBean.senaraiOksForDakwa}" var="oks">
                                    <tr>
                                        <td width="5%">${j+1}</td>
                                        <td width="5%">
                                            <!--<input type="checkbox" name="pilihCheckBox${lineOp_rowNum-1}${j}" id="pilihCheckBox${lineOp_rowNum-1}${j}" value="off" onclick="checkValue('${lineOp_rowNum-1}${j}');">-->
                                            <input type="checkbox" name="pilihCheckBox${lineOp_rowNum-1}${j}" id="pilihCheckBox${lineOp_rowNum-1}${j}" value="${oks.idOrangKenaSyak}" disabled="true">
                                            <input type="hidden" name="pilihOksDakwa${lineOp_rowNum-1}${j}" id="pilihOksDakwa${lineOp_rowNum-1}${j}" value="${oks.idOrangKenaSyak}">
                                            <input type="hidden" size="2" id="OpDakwaOks${lineOp_rowNum-1}${j}" value="${oks.statusDakwaan}">
                                        </td>

                                        <td width="30%">${oks.nama}</td>
                                        <td width="30%">
                                            <c:if test="${oks.noPengenalan eq null}">Tiada data</c:if>
                                            <c:if test="${oks.noPengenalan ne null}">${oks.noPengenalan}</c:if>
                                        </td>
                                        <td width="50%">
                                            <c:set value="1" var="b"/>
                                            <c:forEach items="${actionBean.senaraiBarangRampasanForKenderaan}" var="barang">
                                                <c:if test="${barang.aduanOrangKenaSyak.idOrangKenaSyak eq oks.idOrangKenaSyak}">
                                                    ${b} . ${barang.item} <br>
                                                    <c:set value="${b+1}" var="b"/>
                                                </c:if>
                                            </c:forEach>

                                        </td>
                                    </tr>
                                    <c:set value="${j+1}" var="j"/>
                                </c:forEach>
                            </table>
                        </display:column>


                    </display:table>
                </div>
            </fieldset>
        </c:if>
    </div>
</s:form>
