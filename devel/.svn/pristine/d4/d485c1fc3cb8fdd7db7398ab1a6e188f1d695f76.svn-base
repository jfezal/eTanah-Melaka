<%-- 
    Document   : kpsn_lelong
    Created on : Feb 22, 2011, 4:48:18 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_lelong?showFormB", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }

    function calc(oh,value){
        var deposit = value * 0.1;
        var baki = value - deposit;

        $("#deposit"+oh).val(deposit);
        $("#baki"+oh).val(baki);

        var num = document.getElementById('deposit'+oh).value;

        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit'+oh).val((((sign)?'':'-') + num + '.' + cents));
    }

    function dateValidation(dat,value){
        var now = new Date();
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+121)+'/'+(date.getFullYear());//+1900);
        var dateNew = new Date(date1);
        var sdate = dateNew.getUTCDate()+'/'+(dateNew.getUTCMonth()+1)+'/'+(dateNew.getFullYear());//+1900);
        $('#tarikhBayar'+dat).val(sdate);
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/keputusan_lelong?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/lelong/keputusan_lelong?cetak&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    var n = "";
    function validate(lol,input) {
        if (input.length == 0) {
            alert ('Sila Masukkan Harga Rizab');
            $('#ejaRizab'+lol).val("");
            return true;
        }

        var num = document.getElementById('hargaRizab'+lol).value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#hargaRizab'+lol).val((((sign)?'':'-') + num + '.' + cents));
    }

    var n = "";
    function changeFormat2(input) {

        if (input.length == 0) {
            alert ('Sila Masukkan Amaun Tertunggak');
            $('#amaunTung').val("");
            return true;
        }

        var num = document.getElementById('amaunTung').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#amaunTung').val((((sign)?'':'-') + num + '.' + cents));
    }
    
    function convertDay(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
        var date1 = date.getDay();
        var hari = "";
        switch(date1){
            case 0:  hari = "Ahad";
                break;
            case 1:  hari = "Isnin";
                break;
            case 2:  hari = "Selasa";
                break;
            case 3:  hari = "Rabu";
                break;
            case 4:  hari = "Khamis";
                break;
            case 5:  hari = "Jumaat";
                break;
            case 6:  hari = "Sabtu";
                break;
            default :hari = "salah";
                break;
        }
        $('#hari').val(hari);
    }
    
    function viewDetail(id){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_bidaan?viewDetail&idLelong="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    
    function addRow() {
        var table = document.getElementById("add");
        var rowcount = table.rows.length;
        var rowNum = $('#rowCount3').val();
        if(rowNum == null){
            rowNum = 0;
        }
        var rowCount1 = (parseInt(rowNum)+1);
        var row = table.insertRow(rowcount);
        var cell2 = row.insertCell(0);
        cell2.innerHTML = "<p><label>&nbsp;</label>RM</p>";

        var cell1 = row.insertCell(1);
        var e1 = document.createElement("INPUT");
        e1.name = 'amaunTunggakan2';
        e1.id = 'amaunTung2';
        e1.type = 'text';
        e1.setAttribute("formatPattern", "###,###,###.00");
        e1.onblur = function(){changeFormat3(this);};
        e1.style.width = '150px';
        cell1.appendChild(e1);
        
        var cell2 = row.insertCell(2);
        cell2.innerHTML = "&nbsp;";

        //        var cell3 = row.insertCell(3);
        //        var e1 = document.createElement("img");
        //        e1.src = "${pageContext.request.contextPath}/images/not_ok.gif";
        //        e1.alt = "Klik Untuk Hapus1";
        //        e1.align = "top"
        //        e1.value="Hapus";
        //        e1.height = "15";
        //        e1.width = "15";
        //        e1.id =rowcount;
        //        e1.onclick=function(){deleteRow(this.id);};
        //        cell3.appendChild(e1);

        $('#rowCount3').val(parseInt(rowCount1));

    }
</script>


<s:form beanclass="etanah.view.stripes.lelong.KeputusanLelongActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <c:if test="${actionBean.lel3 ne true}">
        <c:if test="${actionBean.error eq true}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Perintah Jualan
                </legend>

                <c:if test="${actionBean.lelong.bil ne null}">
                    <p>
                        <label>Status lelongan : </label>
                        <c:if test="${actionBean.lelong.bil eq '1'}">
                            Kali Pertama
                        </c:if>
                        <c:if test="${actionBean.lelong.bil eq '2'}">
                            Kali Kedua
                        </c:if>
                        <c:if test="${actionBean.lelong.bil eq '3'}">
                            Kali Ketiga
                        </c:if>
                    </p><br>
                </c:if>

                <p>
                    <c:set value="1" var="dat"/>
                    <label><font color="red">*</font>Tarikh Lelongan Awam :</label>
                    <s:text name="tarikhLelong" id="datepicker" onclick="show_calendar();" formatPattern="dd/MM/yyyy"/>&nbsp;
                    <%--<s:text name="tarikhLelong" id="datepicker" class="datepicker" onchange="dateValidation(${dat},this.value)"/>&nbsp;
                    <a href="javascript:show_calendar();">
                        <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>--%>
                    <font color="red" size="1">(cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Masa Lelongan Awam :</label>
                    <s:select name="jam" style="width:56px">
                        <s:option value="00">Jam</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>:
                    <s:select name="minit" style="width:65px">
                        <s:option value="00">Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>:

                    <s:select name="ampm" style="width:80px">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="AM">Pagi</s:option>
                        <s:option value="PM">Petang</s:option>
                    </s:select>

                <p>
                    <label><font color="red">*</font> Hari :</label>
                    <s:text id="hari" name="lelong.tarikhLelong" disabled="true" formatPattern="EEEE" />
                </p>

                <p>
                    <label><font color="red">*</font> Tempat :</label>
                    <s:textarea id="tempat" name="lelong.tempat" cols="50" rows="5"/>
                </p>

                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <s:text id="tarikhBayar1" name="tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" readonly="false" />&nbsp;(120 hari dari tarikh lelongan)
                </p>
                <br>

                <%--for negori Melako jo--%>
                <c:if test="${actionBean.negori eq true}">
                    <c:set var="i" value="0" />
                    <p>
                        <label><font color="red">*</font>Jumlah Keseluruhan Hutang : </label>RM

                        <s:text id="amaunTung" name="amaunTunggakan" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);" onkeyup="validateNumber(this,this.value);"/>&nbsp;                        
                        <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Jumlah Hutang"
                             onmouseover="this.style.cursor='pointer';" onclick="addRow();return false;" title="Tambah Jumlah Hutang">

                    </p>

                    <c:if test="${actionBean.amaunTunggakan2 ne null}">
                        <p>
                            <label>&nbsp;</label>RM
                            <s:text id="amaunTung2" name="amaunTunggakan2" style="width:150px;" formatPattern="###,###,###.00" onblur="changeFormat2(this.value);"/>&nbsp;
<!--                            <img src="${pageContext.request.contextPath}/images/not_ok.gif" height="15" width="15" alt="Klik Untuk Hapus"
                                 onmouseover="this.style.cursor='pointer';" onclick="deleteRow3('${line.idEnkuiriPeminjam}');return false;"  title="Klik Untuk Hapus">-->
                        </p>
                    </c:if>

                    <table id ="add" border="0"></table>
                    <c:set var="i" value="${i+1}" />
                    <s:hidden name="rowCount3"  id="rowCount3"/>
                </c:if>

                <div class="content" align="center">
                    <c:if test="${same eq false}">
                        <display:table class="tablecloth" name="${actionBean.listLel}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                            <display:column title="Harga Rizab" >
                                RM <s:text name="listLel[${line_rowNum - 1}].hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                                </s:text>
                            </display:column>
                            <display:column title="Deposit">
                                RM <s:text name="listLel[${line_rowNum - 1}].deposit" value="${line.deposit}" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00"/>
                            </display:column>
                        </display:table>
                    </c:if>
                    <c:if test="${same eq true}">
                        <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" value="${actionBean.idHak}"/>
                            <display:column title="Harga Rizab" >
                                RM <s:text name="enkuiri.hargaRizab" id="hargaRizab${line_rowNum}" onkeyup="validateNumber(this,this.value);" onchange="calc(${line_rowNum},this.value);" onblur="validate(${line_rowNum},this.value)" formatPattern="###,###,###.00">
                                    ${actionBean.enkuiri.hargaRizab}
                                </s:text>
                            </display:column>
                            <display:column title="Deposit">
                                RM <s:text name="enkuiri.deposit" id="deposit${line_rowNum}" readonly="true" formatPattern="###,###,###.00">
                                    ${actionBean.enkuiri.deposit}
                                </s:text>
                            </display:column>
                        </display:table>
                    </c:if>
                </div>
                <%--berasingan--%>
                <c:if test="${same eq false}">
                    <p align="right">
                        <s:button name="simpanLelong" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <%--<s:reset name="isiMula" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;--%>
                    </p>
                </c:if>
                <%--bersama--%>
                <c:if test="${same eq true}">
                    <p align="right">
                        <s:button name="saveBersama" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        <%--<s:reset name="isiMulaBersama" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;--%>
                    </p>
                </c:if>

                <c:if test="${actionBean.disabled ne null}">
                    <p align="right">
                        <s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn" onclick="edit1(this.form, this.name, 'page_div');"/>
                    </p>
                </c:if>
            </fieldset>

            <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
                <fieldset class="aras1">
                    <legend>
                        Sejarah Siasatan
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                            <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                            <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                            <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                            <display:column property="status.nama" title="Status" class=""/>
                            <%--<display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>--%>
                        </display:table>
                    </div>
                </fieldset>
            </c:if>
            <c:if test="${fn:length(actionBean.senaraiLelongan3)>0}">
                <fieldset class="aras1">
                    <legend>
                        Sejarah Lelongan
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiLelongan3}" id="line" >
                            <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                            <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                            <%--<display:column title="Bilangan Lelongan" property="bil"/>--%>
                            <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                            <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                            <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                            <display:column title="Harga Rizab (RM)">
                                <div align="right">
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                                </div>
                            </display:column>
                            <display:column title="Deposit (RM)">
                                <div align="right">
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                                </div>
                            </display:column>
                            <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>

                            <c:if test="${line.kodStatusLelongan.kod ne 'TB'}">
                                <display:column title="Status" >
                                    <a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line.kodStatusLelongan.nama}</a>
                                </display:column>
                            </c:if>

                            <c:if test="${line.kodStatusLelongan.kod eq 'TB'}">
                                <display:column  title="Status" class="">${line.kodStatusLelongan.nama}</display:column>

                            </c:if>
                            <c:if test="${actionBean.negori eq true}">
                                <display:column title="Jurulelong">
                                    <c:if test="${line.jurulelong.idJlb eq null}">   
                                        -
                                    </c:if>
                                    <c:if test="${line.jurulelong.idJlb ne null}">   
                                        ${line.jurulelong.nama}
                                    </c:if>
                            
                                </display:column>
                            </c:if>
                            <c:if test="${actionBean.negori eq false}">
                                <display:column title="Syarikat Jurulelong">
                                    <c:if test="${line.sytJuruLelong.idSytJlb eq null}">   
                                        -
                                    </c:if>
                                    <c:if test="${line.sytJuruLelong.idSytJlb ne null}">   
                                        ${line.sytJuruLelong.nama}
                                    </c:if>

                                </display:column>
                            </c:if>

                        </display:table>
                    </div>
                </fieldset>
            </c:if>
        </c:if>
    </c:if>
</s:form>
