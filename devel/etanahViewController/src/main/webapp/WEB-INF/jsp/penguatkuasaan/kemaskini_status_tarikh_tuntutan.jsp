<%--
    Document   : kemaskini_status_tarikh_tuntutan
    Created on : Ogos 7, 2012, 2:16:37 AM
    Author     : sitifariza.hanim
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
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
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        jenisPengenalan();
        
        var bilOp =  ${fn:length(actionBean.listOp)};
        for (var p = 0; p < bilOp; p++){
            var totalBarang = document.getElementById("totalBarang"+p).value;
            //                        alert(totalBarang);
            for (var b = 0; b < totalBarang; b++){
                
                var tarikh = document.getElementById("tarikhTuntut"+p+b).value;
                //                                alert(tarikh +":: index"+p+b);
                if(tarikh != ""){
                    var vsplit = tarikh.split('/');
                    var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];

                    var sdate = new Date(fulldate);
                    var expDate = sdate;
                    expDate.setDate(sdate.getDate() + 30);
                
                    var dd = expDate.getDate();
                    var mm = expDate.getMonth() + 1;
                    var y = expDate.getFullYear();

                    var someFormattedDate = dd + '/'+ mm + '/'+ y;
                    //                    alert("new :"+someFormattedDate);
                
                    $('#displayDays'+p+b).append(someFormattedDate); 
                }
                
                if($("#statusBarangUntukDijual"+p+b).val() == "Y"){
                    document.getElementById("pilihBarang"+p+b).checked = true;
                    //                        document.getElementById("pilihCheckBox"+p+b).value = "on";
                } 
            }
            
         
            
        }

    });

    function popup(idBarang){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_barang_tahanan?viewBarangDetail&idBarang='+idBarang;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function test(f) {
        $(f).clearForm();
    }

    function validate(){
        return true;
    }
    
    function doAgih(e, f) {
    
        var nota = $('#permohonanNota').val();
        if(nota == 'false'){
            alert('Sila isi maklumat nota/kertas minit terlebih dahulu.');
            return false;
        }
        if(confirm('Adakah anda pasti? Sila semak tab yang lain terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
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
    
    function validateFormJualan(){
        //        alert("validateFormJualan");

        if($('#nilaiJualan').val() == "")
        {
            alert("Sila masukkan nilai jualan");
            return false;
            $('#nilaiJualan').focus();
        }
        
        return true;
            

    }
    function jenisPengenalan(){
        if($('#pengenalan').val() == 'B'){
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            $('#noPengenalanLain').hide();

        }else{
            $('#noPengenalanLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';

        }
    }
    
    function returnCurrency(amount){
        document.getElementById('nilaiJualan').value = CurrencyFormatted(amount);
    }

    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.KemaskiniStatusBarangRampasan">
    <s:hidden name="statusNotaExist" id="permohonanNota"/>
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Barang Rampasan
            </legend>
            <div class="content" align="center">
                <c:if test="${!multipleOp && !viewMultipleOp && !addKeputusanBarangRampasan && !viewKeputusanBarangRampasan && !editBayaranJualan && !viewBayaranJualan}">
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN:</font> Sila kemaskini status barang rampasan dan tarikh tuntutan.
                    </div>&nbsp;
                    <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/penguatkuasaan/kemaskini_status_barang_rampasan">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Barang Rampasan"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                        <display:column title="Kuantiti">
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                Sebuah
                            </c:if>
                            <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                ${line.kuantiti} ${line.kuantitiUnit}
                            </c:if>
                        </display:column>
                        <display:column title="Tempat Simpanan">
                            <c:if test="${line.namaPemunya eq null}">
                                Tiada data
                            </c:if>
                            <c:if test="${line.namaPemunya ne null}">
                                ${line.namaPemunya}
                            </c:if>
                        </display:column>
                        <display:column title="Tempat Simpanan">
                            <c:if test="${line.tempatSimpanan eq null}">
                                Tiada data
                            </c:if>
                            <c:if test="${line.tempatSimpanan ne null}">
                                ${line.tempatSimpanan}
                            </c:if>
                        </display:column>
                        <display:column title="Status">
                            <input name="idBarang${line_rowNum-1}" value="${line.idBarang}" type="hidden">
                            <s:select name="status${line_rowNum-1}" id="status" value="${line.status.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                            </s:select>&nbsp;
                        </display:column>
                        <%--<display:column title="Tarikh Tuntutan">
                           <c:if test="${line.tarikhTuntut eq null}">
                               Tiada data
                           </c:if>
                           <c:if test="${line.tarikhTuntut ne null}">
                               ${line.tarikhTuntut}
                           </c:if>
                       </display:column>--%>

                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Simpan" name="simpan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </c:if>


                <c:if test="${multipleOp}">
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN:</font> Sila kemaskini status barang rampasan dan tarikh tuntutan.
                    </div>&nbsp;
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan">
                            <c:set value="0" var="count"/>

                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Papar</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Tuntutan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <tr>
                                        <td width="5%">${count+1}</td>
                                        <td width="50%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                        <td width="30%">
                                            <c:if test="${barang.imej.namaFizikal != null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                            </c:if>
                                        </td>
                                        <td width="30%">
                                            <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                            <s:select name="status${line_rowNum-1}${count}" id="status" value="${barang.status.kod}">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                            </s:select>&nbsp;
                                        </td>
                                        <td width="30%">
                                            <s:text name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" size="12" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;
                                        </td>
                                    </tr>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Simpan" name="simpanStatusBarangTuntutan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${viewMultipleOp}">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi" style="width:300px;">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan" style="width:700px;">
                            <c:set value="0" var="count"/>

                            <table width="70%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Papar</b></th>
                                    <th  width="50%" align="center"><b>Status Barang</b></th>
                                    <th  width="50%" align="center"><b>Tarikh Tuntutan</b></th>
                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <tr>
                                        <td width="5%">${count+1}</td>
                                        <td width="20%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                        <td width="10%">
                                            <c:if test="${barang.imej.namaFizikal != null}">
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${barang.imej.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${barang.imej.kodDokumen.nama}"/>
                                            </c:if>
                                        </td>
                                        <td width="50%">
                                            ${barang.status.nama}
                                        </td>
                                        <td width="50%">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>
                                        </td>

                                    </tr>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                        </display:column>
                    </display:table>

                </c:if>

                <c:if test="${addKeputusanBarangRampasan}">
                    <div class="instr-fieldset">
                        <font color="red">MAKLUMAN:</font> Sila kemaskini status barang rampasan dan tarikh tuntutan.
                    </div>&nbsp;
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan">
                            <input type="hidden" value="${fn:length(line.senaraiBarangRampasan)}" id="totalBarang${line_rowNum-1}">
                            <c:set value="0" var="count"/>

                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Rampasan</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Tamat Tuntutan</b></th>
                                    <th  width="5%" align="center"><b>Status Keputusan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <tr>
                                        <td width="5%">${count+1}</td>
                                        <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                        <td width="30%">
                                            <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                            ${barang.status.nama}&nbsp;
                                        </td>
                                        <td width="20%">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                            <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                        </td>
                                        <td width="20%">
                                            <div id="displayDays${line_rowNum-1}${count}"></div>
                                        </td>
                                        <td width="30%">
                                            <s:select name="statusKeputusan${line_rowNum-1}${count}" id="statusKeputusan" value="${barang.keputusan.kod}">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:option value="LH">Lucuthak</s:option>
                                                <s:option value="RM">Rujuk Mahkamah</s:option>
                                            </s:select>&nbsp;
                                        </td>
                                    </tr>
                                    <c:set value="${count+1}" var="count"/>
                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>

                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Simpan" name="simpanKeputusan" id="simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                                <s:button name="createPermohonan" id="createPermohonan" value="Selesai" class="btn" onclick="doAgih(this.name, this.form);"/>
                            </td>
                        </tr>
                    </table>
                </c:if>

                <c:if test="${viewKeputusanBarangRampasan}">

                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan">
                            <input type="hidden" value="${fn:length(line.senaraiBarangRampasan)}" id="totalBarang${line_rowNum-1}">
                            <c:set value="0" var="count"/>

                            <table width="100%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Rampasan</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Tamat Tuntutan</b></th>
                                    <th  width="5%" align="center"><b>Status Keputusan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <c:choose>
                                        <c:when test="${actionBean.stageLucutHak eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'LH'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${actionBean.stageRujukMahkamah eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'RM'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>

                </c:if>
                <br>
                <br>
            </div>
        </fieldset>
    </div>
    <c:if test="${editBayaranJualan}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="instr-fieldset">
                    <font color="red">MAKLUMAN:</font> Sila masukkan jumlah jualan.
                </div>&nbsp;
                <br>
                <p>
                    <label>&nbsp;</label>
                </p>
                <br>
                <p>
                    <label>Jumlah Jualan (RM) :</label>
                    <s:text id="nilaiJualan" name="nilaiJualan" formatPattern="0.00" onblur="returnCurrency(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                </p>

                <p>
                    <label>Nama :</label>
                    <s:text name="namaPenerima" id="namaPenerima" size="42" maxlength="250" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                    <s:select name="kodPengenalanPenerima.kod"  value="${actionBean.permohonan.kodPengenalanPenerima.kod}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p id="noPengenalanLain">
                    <label>No.Pengenalan :</label>
                    <s:text name="noPengenalanPenerimaLain" id="noPengenalanPenerimaLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                    &nbsp;
                </p>
                <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                    <label>No.Pengenalan :</label>
                    <s:text name="noPengenalanPenerimaBaru" id="noPengenalanPenerimaBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                    <font color="red" size="1">cth : 850510075342 </font>
                    &nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    <s:text name="alamatPenerima1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamatPenerima2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamatPenerima3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="alamatPenerima4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    &nbsp;
                </p>


                <br>

                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan">
                            <input type="hidden" value="${fn:length(line.senaraiBarangRampasan)}" id="totalBarang${line_rowNum-1}">
                            <c:set value="0" var="count"/>

                            <table width="50%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Rampasan</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Tamat Tuntutan</b></th>
                                    <th  width="5%" align="center"><b>Status Keputusan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <c:choose>
                                        <c:when test="${actionBean.stageLucutHak eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'LH'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${actionBean.stageRujukMahkamah eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'RM'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}"/>
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td width="5%">${count+1}</td>
                                                <td width="30%">
                                                    <input name="statusBarangUntukDijual${line_rowNum-1}${count}" id="statusBarangUntukDijual${line_rowNum-1}${count}" value="${barang.dilepasNoKP}" type="hidden">
                                                    <s:checkbox name="pilihBarang${line_rowNum-1}${count}" id="pilihBarang${line_rowNum-1}${count}" value="${barang.idBarang}"/>
                                                    <a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>
                                                </td>
                                                <td width="30%">
                                                    <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                    ${barang.status.nama}&nbsp;
                                                </td>
                                                <td width="20%">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                    <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                </td>
                                                <td width="20%">
                                                    <div id="displayDays${line_rowNum-1}${count}"></div>
                                                </td>
                                                <td width="30%">
                                                    <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                    <s:select name="status${line_rowNum-1}${count}" id="status" value="${barang.status.kod}">
                                                        <s:option value="">Sila Pilih</s:option>
                                                        <s:options-collection collection="${actionBean.senaraiKodStatus}" label="nama" value="kod" />
                                                    </s:select>&nbsp;
                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>
                    <table>
                        <tr>
                            <td align="center">
                                <s:button class="btn" value="Simpan" name="simpanBayaranJualan" id="simpan" onclick="if(validateFormJualan())doSubmit(this.form, this.name,'page_div')"/>
                            </td>
                        </tr>
                    </table>
                </div>

            </fieldset>
        </div>

    </c:if>
    <c:if test="${viewBayaranJualan}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="instr-fieldset">
                </div>&nbsp;
                <br>
                <p>
                    <label>&nbsp;</label>
                </p>
                <br>

                <div class="content" align="center">
                    <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Maklumat Laporan Operasi">
                            <table width="20%" cellpadding="1">
                                <tr>
                                    <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                    <td width="20%"><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></td>
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
                        <display:column title="Maklumat Barang Rampasan">
                            <input type="hidden" value="${fn:length(line.senaraiBarangRampasan)}" id="totalBarang${line_rowNum-1}">
                            <c:set value="0" var="count"/>

                            <table width="50%" cellpadding="1">
                                <tr>
                                    <th  width="1%" align="center"><b>Bil</b></th>
                                    <th  width="1%" align="center"><b>Item</b></th>
                                    <th  width="5%" align="center"><b>Status Barang</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Rampasan</b></th>
                                    <th  width="5%" align="center"><b>Tarikh Tamat Tuntutan</b></th>
                                    <th  width="5%" align="center"><b>Status Keputusan</b></th>

                                </tr>
                                <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                    <c:choose>
                                        <c:when test="${actionBean.stageLucutHak eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'LH'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${actionBean.stageRujukMahkamah eq true}">
                                            <c:if test="${barang.keputusan.kod eq 'RM'}">
                                                <tr>
                                                    <td width="5%">${count+1}</td>
                                                    <td width="30%"><a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a></td>
                                                    <td width="30%">
                                                        <s:checkbox name="pilihBarang${line_rowNum-1}" id="pilihBarang${line_rowNum-1}" value="${line.idBarang}"/>
                                                        <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                        ${barang.status.nama}&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                        <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                    </td>
                                                    <td width="20%">
                                                        <div id="displayDays${line_rowNum-1}${count}"></div>
                                                    </td>
                                                    <td width="30%">
                                                        ${barang.keputusan.nama}
                                                    </td>
                                                </tr>
                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td width="5%">${count+1}</td>
                                                <td width="30%">
                                                    <input name="statusBarangUntukDijual${line_rowNum-1}${count}" id="statusBarangUntukDijual${line_rowNum-1}${count}" value="${barang.dilepasNoKP}" type="hidden">
                                                    <s:checkbox name="pilihBarang${line_rowNum-1}${count}" id="pilihBarang${line_rowNum-1}${count}" value="${barang.idBarang}" disabled="true"/>
                                                    <a class="popup" onclick="popup(${barang.idBarang})">${barang.item}</a>
                                                </td>
                                                <td width="30%">
                                                    <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                    ${barang.status.nama}&nbsp;
                                                </td>
                                                <td width="20%">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhTuntut}"/>&nbsp;
                                                    <s:hidden name="tarikhTuntut${line_rowNum-1}${count}" id="tarikhTuntut${line_rowNum-1}${count}" value="${barang.tarikhTuntut}" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                                                </td>
                                                <td width="20%">
                                                    <div id="displayDays${line_rowNum-1}${count}"></div>
                                                </td>
                                                <td width="30%">
                                                    <input name="idBarang${line_rowNum-1}${count}" value="${barang.idBarang}" type="hidden">
                                                    ${barang.status.nama}&nbsp;
                                                </td>
                                            </tr>
                                            <c:set value="${count+1}" var="count"/>
                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>
                            </table>
                            <%-- <b>Jumlah : ${fn:length(line.senaraiBarangRampasan)}</b>--%>
                        </display:column>
                    </display:table>
                    <br>
                    <legend>
                        Maklumat Jualan
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiJualan}" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                            <display:column title="Jumlah Jualan(RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                            <display:column title="Status Bayaran">
                                <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                                <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                            </display:column>
                            <display:column title="Tarikh Bayar">
                                <c:forEach items="${actionBean.senaraiPermohonanTuntutanBayar}" var="ptb">
                                    <c:if test="${ptb.permohonanTuntutanKos.idKos eq line.permohonanTuntutanKos.idKos}">
                                        <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${ptb.tarikhBayar}"/>
                                    </c:if>
                                </c:forEach>
                            </display:column>
                        </display:table>
                    </div>

                </div>

            </fieldset>
        </div>
    </c:if>
</s:form>
